package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.ReservaDTO;
import com.Equipo2.RaceACar.DTO.UsuarioSinPassDTO;
import com.Equipo2.RaceACar.DTO.ValoracionDTO;
import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Reserva;
import com.Equipo2.RaceACar.model.Valoracion;
import com.Equipo2.RaceACar.repository.AutoRepository;
import com.Equipo2.RaceACar.repository.ReservaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    @Autowired
    private MailService mailService;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ValoracionService valoracionService;

    public ReservaDTO crearReserva(Long autoId, LocalDate fechaComienzo, LocalDate fechaFin, String formaDePago, Usuario usuario, String recogida, String entrega) {
        Auto auto = autoRepository.findById(autoId).orElseThrow(() -> new EntityNotFoundException("Auto not found"));

        if (puedeReservar(auto, fechaComienzo, fechaFin)) {
            Reserva reservation = new Reserva();
            reservation.setAuto(auto);
            reservation.setFechaComienzo(fechaComienzo);
            reservation.setFechaFin(fechaFin);
            reservation.setFormaDePago(formaDePago);
            reservation.setUsuario(usuario);
            reservation.setRecogida(recogida);
            reservation.setEntrega(entrega);
            reservation.setTotal(calcularTotalReserva(fechaComienzo, fechaFin, auto));
            autoRepository.save(auto);
            System.out.println(auto);
            mailService.sendMailReservation(usuario.getEmail(),reservation);
            reservaRepository.save(reservation);
            return modelMapper.map(reservation, ReservaDTO.class);
        } else {
            throw new IllegalArgumentException("El auto no está disponible para las fechas especificadas");
        }
    }
    private Double calcularTotalReserva(LocalDate fechaComienzo, LocalDate fechaFin, Auto auto) {
        long diasReserva = ChronoUnit.DAYS.between(fechaComienzo, fechaFin);
        return diasReserva * auto.getValor();
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
                    .map(reserva -> {
                        Valoracion valoracion = valoracionService.getValoracionPorReservaId(reserva.getId());
                        ValoracionDTO valoracionDTO = mapper.convertValue(valoracion, ValoracionDTO.class);


                        ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
                        reservaDTO.setUsuario(mapper.convertValue(reserva.getUsuario(), UsuarioSinPassDTO.class));
                        if(valoracionDTO != null){
                            valoracionDTO.setNombre(reserva.getUsuario().getNombre());
                            valoracionDTO.setApellido(reserva.getUsuario().getApellido());
                        }
                        reservaDTO.setValoracion(valoracionDTO);
                        return reservaDTO;
                    })
                    .collect(Collectors.toList());
        }
    }
    public void marcarAutoComoDisponible(Reserva reserva) {
        Auto auto = reserva.getAuto();
        auto.setDisponible(true);
        autoRepository.save(auto);
    }


    public List<LocalDate> obtenerFechasInhabilitadasSegunAutoId(Long autoId) {
        Auto auto = autoRepository.findById(autoId)
                .orElseThrow(() -> new EntityNotFoundException("Auto not found"));

        List<Reserva> reservas = auto.getReservas();

        List<LocalDate> fechasInhabilitadas = new ArrayList<>();

        for (Reserva reserva : reservas) {
            LocalDate fechaInicio = reserva.getFechaComienzo();
            LocalDate fechaFin = reserva.getFechaFin();

            // Agregar todas las fechas desde la fecha de inicio hasta la fecha de fin inclusive
            fechasInhabilitadas.addAll(obtenerFechasEntre(fechaInicio, fechaFin));
        }

        return fechasInhabilitadas;
    }

    private List<LocalDate> obtenerFechasEntre(LocalDate fechaInicio, LocalDate fechaFin) {
        List<LocalDate> fechas = new ArrayList<>();
        LocalDate fechaActual = fechaInicio;

        while (!fechaActual.isAfter(fechaFin)) {
            fechas.add(fechaActual);
            fechaActual = fechaActual.plusDays(1);
        }

        return fechas;
    }

    public List<ReservaDTO> obtenerReservasPorUsuario(Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        List<Reserva> reservas = reservaRepository.findByUsuario(usuario);

        return reservas.stream()
                .map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
                .collect(Collectors.toList());
    }

    public ReservaDTO obtenerUltimaReservaPorUsuario(Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        Reserva ultimaReserva = reservaRepository.findFirstByUsuarioOrderByFechaComienzoDesc(usuario);

        if (ultimaReserva != null) {
            return modelMapper.map(ultimaReserva, ReservaDTO.class);
        } else {
            return null;
        }
    }

    }