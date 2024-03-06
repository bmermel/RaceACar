package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.ReservaDTO;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Reserva;
import com.Equipo2.RaceACar.repository.AutoRepository;
import com.Equipo2.RaceACar.repository.ReservaRepository;
import com.Equipo2.RaceACar.service.AutoService;
import com.Equipo2.RaceACar.service.ReservaService;
import com.Equipo2.RaceACar.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")

public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final AutoRepository autoRepository;

    @Autowired
    ReservaService service;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    public ReservaController(ReservaRepository reservaRepository, AutoRepository autoRepository) {
        this.reservaRepository = reservaRepository;
        this.autoRepository = autoRepository;
    }

    @PostMapping("/crear")
    public ResponseEntity<Reserva> crearReserva(@RequestParam Long autoId, @RequestParam LocalDate fechaComienzo, @RequestParam LocalDate fechaFin) {


        Auto auto = autoRepository.findById(autoId).orElseThrow(() -> new EntityNotFoundException("Auto not found"));

        if (service.puedeReservar(auto, fechaComienzo, fechaFin)) {
            Reserva reserva = new Reserva();
            reserva.setAuto(auto);
            reserva.setFechaComienzo(fechaComienzo);
            reserva.setFechaFin(fechaFin);

            Reserva nuevaReserva = reservaRepository.save(reserva);
            return ResponseEntity.ok(nuevaReserva);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
    @PreAuthorize("hasAuthority('permission:read') || hasRole('ROLE_USER')")
    @GetMapping("/all")
    public ResponseEntity<List<ReservaDTO>> obtenerReservas() {
        try {
            List<ReservaDTO> reservas = service.obtenerReservas();

            if (reservas.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(reservas);
            }
        } catch (Exception ex) {
            System.err.println("Error al obtener la lista de reservas: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    }
