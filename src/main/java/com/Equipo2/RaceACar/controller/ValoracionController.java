package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.ValoracionDTO;
import com.Equipo2.RaceACar.model.Valoracion;
import com.Equipo2.RaceACar.repository.ValoracionRepository;
import com.Equipo2.RaceACar.service.ValoracionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/valoraciones")
@CrossOrigin(origins = "*")

public class ValoracionController {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private ValoracionService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{idAuto}")
    public List<ValoracionDTO> obtenerValoracionesPorIdAuto(@PathVariable Long idAuto) {
        List<Valoracion> valoraciones = valoracionRepository.findByReservaAutoId(idAuto);

        if (valoraciones.isEmpty()) {
            return Collections.emptyList();
        }

        return valoraciones.stream()
                .map(valoracion -> modelMapper.map(valoracion, ValoracionDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{autoId}/promedio")
    public Double obtenerPromedioValoracionPorAutoId(@PathVariable Long autoId) {
        Double promedio = valoracionRepository.obtenerPromedioValoracionPorAutoId(autoId);
        return service.redondearADosDecimales(promedio);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearValoracion(
            @RequestParam Long reservaId,
            @RequestParam Integer valoracion,
            @RequestParam String resena
    ) {
        try {
            ValoracionDTO nuevaValoracion = service.crearValoracion(reservaId, valoracion, resena);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaValoracion);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la reserva con el ID proporcionado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear la valoración.");
        }
    }
}
