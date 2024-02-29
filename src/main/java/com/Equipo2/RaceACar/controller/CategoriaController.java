package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.CategoriaConIdDTO;
import com.Equipo2.RaceACar.DTO.CategoriaDTO;
import com.Equipo2.RaceACar.service.AutoService;
import com.Equipo2.RaceACar.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {
    @Autowired
    CategoriaService service;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping()
    public ResponseEntity<?> crearAuto(@RequestBody CategoriaDTO categoriaDTO) {
        try{
            service.crearCategoria(categoriaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la categoría: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO){
        try {
            service.editarCategoria(id, categoriaDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("La categoría no fue encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar la categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        try {
            service.eliminarCategoria(id);
            return new ResponseEntity<>("Categoría Borrada Correctamente",HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Categoría no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoriaConIdDTO>> obtenerCategorias() {
        try {
            List<CategoriaConIdDTO> categorias = service.obtenerTodasLasCategorias();

            if (categorias.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(categorias);
            }
        } catch (Exception ex) {
            System.err.println("Error al obtener la lista de autos: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
