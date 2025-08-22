package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.RequestReservationDto;
import com.flavors.pe.flavorsfeast.dto.ResponseReservationDto;
import com.flavors.pe.flavorsfeast.models.Reservation;
import com.flavors.pe.flavorsfeast.models.State;
import com.flavors.pe.flavorsfeast.models.User;
import com.flavors.pe.flavorsfeast.repositories.ReservationRepository;
import com.flavors.pe.flavorsfeast.repositories.UserRepository;
import com.flavors.pe.flavorsfeast.services.IReservationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(UserRepository userRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Integer registerReservation(RequestReservationDto requestReservationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmailUser(email).orElseThrow();

        Reservation reservation = Reservation
                .builder()
                .dateReservation(requestReservationDto.getDate())
                .timeReservation(requestReservationDto.getTime())
                .personsReservation(requestReservationDto.getPersons())
                .user(user)
                .state(State.PENDING)
                .locationReservation(requestReservationDto.getLocation())
                .build();

        reservationRepository.save(reservation);
        return reservation.getIdReservation();
    }

    @Override
    public List<ResponseReservationDto> getAllReservations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<Reservation> reservations  = reservationRepository.findAllByEmailUser(email);

        List<ResponseReservationDto> reservationsDtos = reservations.stream().map(reservation ->
                ResponseReservationDto.builder()
                        .date(reservation.getDateReservation())
                        .time(reservation.getTimeReservation())
                        .persons(reservation.getPersonsReservation())
                        .location(reservation.getLocationReservation())
                        .state(reservation.getState())
                        .build()
        ).toList();

        return reservationsDtos;
    }
}
