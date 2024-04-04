package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.CrearReservaDTO;
import com.Equipo2.RaceACar.DTO.ReservaDTO;
import com.Equipo2.RaceACar.DTO.UsuarioSinPassDTO;
import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Reserva;
import com.Equipo2.RaceACar.repository.AutoRepository;
import com.Equipo2.RaceACar.repository.ReservaRepository;
import com.Equipo2.RaceACar.repository.UsuarioRepository;
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
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")


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
    private UsuarioRepository usuarioRepository;

    @Autowired
    public ReservaController(ReservaRepository reservaRepository, AutoRepository autoRepository) {
        this.reservaRepository = reservaRepository;
        this.autoRepository = autoRepository;
    }

    @PostMapping("/crear")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody CrearReservaDTO reservaDTO) {
        try {
            Usuario usuario = mapper.convertValue(usuarioService.buscarPorEmail(reservaDTO.getEmail()), Usuario.class);
            ReservaDTO nuevaReserva = service.crearReserva(reservaDTO.getAutoId(), reservaDTO.getFechaComienzo(),
                    reservaDTO.getFechaFin(), reservaDTO.getFormaDePago(), usuario, reservaDTO.getRecogida(),
                    reservaDTO.getEntrega());
            String mensaje = "Reserva realizada con éxito para el auto con ID " + reservaDTO.getAutoId() +
                    " desde " + reservaDTO.getFechaComienzo() + " hasta " + reservaDTO.getFechaFin();
            return ResponseEntity.ok(nuevaReserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    //@PreAuthorize("hasAuthority('permission:read') || hasRole('ROLE_USER')")
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

    @GetMapping("/{autoId}/fechasInhabilitadas")
    public ResponseEntity<List<LocalDate>> obtenerFechasInhabilitadasSegunAutoId(@PathVariable Long autoId) {
        try {
            List<LocalDate> fechasInhabilitadas = service.obtenerFechasInhabilitadasSegunAutoId(autoId);
            return ResponseEntity.ok(fechasInhabilitadas);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorUsuario(@PathVariable Long usuarioId) {
        List<ReservaDTO> reservasDTO = service.obtenerReservasPorUsuario(usuarioId);
        return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}/ultima")
    public ResponseEntity<ReservaDTO> obtenerUltimaReservaPorUsuario(@PathVariable Long usuarioId) {
        ReservaDTO ultimaReservaDTO = service.obtenerUltimaReservaPorUsuario(usuarioId);
        if (ultimaReservaDTO != null) {
            return new ResponseEntity<>(ultimaReservaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }
