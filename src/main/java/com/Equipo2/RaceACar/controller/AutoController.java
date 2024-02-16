package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.service.AutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/autos")
@CrossOrigin(origins = "*")
public class AutoController {
    @Autowired
    AutoService service;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping()
    public ResponseEntity<?> crearAuto(@RequestBody AutoDTO autoDTO) {
        try {
            service.crearAuto(autoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            return new ResponseEntity<>("Error al crear el auto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAuto(@PathVariable Long id, @RequestBody AutoDTO autoDTO){
        try {
            service.editarAuto(id, autoDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("El auto no fue encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar el auto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAuto(@PathVariable Long id) {
        try {
            service.eliminarAuto(id);
            return new ResponseEntity<>("Auto Borrado Correctamente",HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Auto no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el auto", HttpStatus.NOT_FOUND);
        }
    }


}
