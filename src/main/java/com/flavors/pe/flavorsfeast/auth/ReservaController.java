package com.flavors.pe.flavorsfeast.auth;

import com.flavors.pe.flavorsfeast.dto.RegistrarReservaDto;
import com.flavors.pe.flavorsfeast.services.impl.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/v2/")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("reserva")
    public ResponseEntity<?> registrarReserva(@RequestBody RegistrarReservaDto registrarReservaDto) {
        reservaService.registrarReserva(registrarReservaDto);
        Map<String, String> response = Map.of("mensaje", "Reserva existosa!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("reservas")
    public ResponseEntity<?> listarReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }
}
