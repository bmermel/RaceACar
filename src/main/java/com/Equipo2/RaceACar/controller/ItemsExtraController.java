package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.ItemsExtraDTO;
import com.Equipo2.RaceACar.service.ItemsExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/items-extra")
@CrossOrigin(origins = "*")

public class ItemsExtraController {

    @Autowired
    private ItemsExtraService itemsExtraService;

    @PostMapping("/crear")
    public ResponseEntity<ItemsExtraDTO> crearItemExtra(@RequestBody String nombre, @RequestBody Integer precio) {
        try {
            ItemsExtraDTO nuevoItemExtra = itemsExtraService.crearItemExtra(nombre, precio);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItemExtra);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{itemExtraId}")
    public ResponseEntity<Void> eliminarItemExtra(@PathVariable Long itemExtraId) {
        try {
            itemsExtraService.eliminarItemExtra(itemExtraId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/editar/{itemExtraId}")
    public ResponseEntity<ItemsExtraDTO> editarItemExtra(@PathVariable Long itemExtraId,
                                                         @RequestBody String nuevoNombre,
                                                         @RequestBody Integer nuevoPrecio) {
        try {
            ItemsExtraDTO itemExtraActualizado = itemsExtraService.editarItemExtra(itemExtraId, nuevoNombre, nuevoPrecio);
            return ResponseEntity.ok(itemExtraActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ItemsExtraDTO>> obtenerTodosLosItemsExtra() {
        try {
            List<ItemsExtraDTO> itemsExtra = itemsExtraService.obtenerTodosLosItemsExtra();
            return ResponseEntity.ok(itemsExtra);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}