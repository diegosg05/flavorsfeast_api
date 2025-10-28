package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.ReservationDto;
import com.flavors.pe.flavorsfeast.exception.BadRequestException;
import com.flavors.pe.flavorsfeast.exception.ResourceNotFoundException;
import com.flavors.pe.flavorsfeast.exception.UnauthorizedException;
import com.flavors.pe.flavorsfeast.mapper.ReservationMapper;
import com.flavors.pe.flavorsfeast.repositories.ReservationRepository;
import com.flavors.pe.flavorsfeast.repositories.UserRepository;
import com.flavors.pe.flavorsfeast.util.ReservationState;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements com.flavors.pe.flavorsfeast.services.ReservationService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(UserRepository userRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationDto registerReservation(ReservationDto reservationDto, String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No se encontró al usuario con email: " + email));

        var newReservation = ReservationMapper.toEntity(reservationDto);
        newReservation.setUser(user);
        var reservationSaved = reservationRepository.save(newReservation);
        return ReservationMapper.toDto(reservationSaved);
    }

    @Override
    public List<ReservationDto> getAllReservationsByUser(String email) {
        var reservations  = reservationRepository.findAllByUser(email);

        return reservations
                .stream()
                .map(ReservationMapper::toDto)
                .toList();
    }

    @Override
    public void cancelReservation(String uid, String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No se encontró al usuario con email: " + email));

        var reservation = reservationRepository.findByUid(uid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontró la reservación con id: " + uid));

        if (user.getId().equals(reservation.getUser().getId()))
            throw new UnauthorizedException("La reservación al cual desea cancelar no está permitida");
        else if (reservation.getState().equals(ReservationState.CANCELED))
            throw new BadRequestException("La reservación ya está cancelada");

        reservation.setState(ReservationState.CANCELED);
        reservationRepository.save(reservation);
    }
}
