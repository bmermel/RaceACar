package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.ItemsDTO;
import com.Equipo2.RaceACar.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @PostMapping("/crear")
    public ResponseEntity<ItemsDTO> crearItem(@RequestParam String nombre) {
        try {
            ItemsDTO nuevoItem = itemsService.crearItem(nombre);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{itemId}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long itemId) {
        try {
            itemsService.eliminarItem(itemId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/editar/{itemId}")
    public ResponseEntity<ItemsDTO> editarItem(@PathVariable Long itemId, @RequestParam String nuevoNombre) {
        try {
            ItemsDTO itemActualizado = itemsService.editarItem(itemId, nuevoNombre);
            return ResponseEntity.ok(itemActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ItemsDTO>> obtenerTodosLosItems() {
        try {
            List<ItemsDTO> items = itemsService.obtenerTodosLosItems();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
