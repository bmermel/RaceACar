package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.ReservaDTO;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Reserva;
import com.Equipo2.RaceACar.repository.AutoRepository;
import com.Equipo2.RaceACar.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AutoRepository autoRepository;

    public Reserva crearReserva(Long autoId, LocalDate fechaComienzo, LocalDate fechaFin) {
        Auto auto = autoRepository.findById(autoId).orElseThrow(() -> new EntityNotFoundException("Auto not found"));

        if (puedeReservar(auto, fechaComienzo, fechaFin)) {
            Reserva reservation = new Reserva();
            reservation.setAuto(auto);
            reservation.setFechaComienzo(fechaComienzo);
            reservation.setFechaFin(fechaFin);
            //marca el auto no disponible
            auto.setDisponible(false);
            autoRepository.save(auto);

            return reservaRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("El auto no está disponible para las fechas especificadas");
        }
    }

    public boolean puedeReservar(Auto auto, LocalDate fechaComienzo, LocalDate fechaFin) {
        List<Reserva> reservations = reservaRepository.findByAuto(auto);

        for (Reserva reserva : reservations) {
            LocalDate reservaInicio = reserva.getFechaComienzo();
            LocalDate reservaFin = reserva.getFechaFin();

            if ((fechaComienzo.isBefore(reservaFin) || fechaComienzo.isEqual(reservaFin)) &&
                    (fechaFin.isAfter(reservaInicio) || fechaFin.isEqual(reservaInicio))) {
                return false;
            }
        }

        return true;
    }

    public List<ReservaDTO> obtenerReservas (){
        List<Reserva> reservaList = reservaRepository.findAll();

        if (reservaList.isEmpty()) {
            System.out.println("La lista está aún vacía");
            return Collections.emptyList();
        } else {
            return reservaList.stream()
                    .map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
                    .collect(Collectors.toList());
        }
    }
    public void marcarAutoComoDisponible(Reserva reserva) {
        Auto auto = reserva.getAuto();
        auto.setDisponible(true);
        autoRepository.save(auto);
    }
    }