package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.ListarReservaDto;
import com.flavors.pe.flavorsfeast.models.Estado;
import com.flavors.pe.flavorsfeast.dto.RegistrarReservaDto;
import com.flavors.pe.flavorsfeast.models.Reserva;
import com.flavors.pe.flavorsfeast.models.Usuario;
import com.flavors.pe.flavorsfeast.repositories.ReservaRepository;
import com.flavors.pe.flavorsfeast.repositories.UsuarioRepository;
import com.flavors.pe.flavorsfeast.services.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public void registrarReserva(RegistrarReservaDto registrarReservaDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow();

        Reserva reserva = Reserva
                .builder()
                .fechaReserva(registrarReservaDto.getFechaReserva())
                .horaReserva(registrarReservaDto.getHoraReserva())
                .personas(registrarReservaDto.getPersonas())
                .usuario(usuario)
                .estado(Estado.pendiente)
                .sucursal(registrarReservaDto.getSucursal())
                .build();

        reservaRepository.save(reserva);
    }

    @Override
    public List<ListarReservaDto> listarReservas() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();

        List<Reserva> reservas  = reservaRepository.findAllByUsuarioCorreo(correo);

        return reservas.stream().map(
                reserva ->
                    ListarReservaDto
                            .builder()
                            .fechaReserva(reserva.getFechaReserva())
                            .horaReserva(reserva.getHoraReserva())
                            .personas(reserva.getPersonas())
                            .sucursal(reserva.getSucursal())
                            .estado(reserva.getEstado().name())
                            .build()
        ).toList();
    }
}
