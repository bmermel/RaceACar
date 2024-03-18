package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.CategoriaConIdDTO;
import com.Equipo2.RaceACar.DTO.CategoriaDTO;
import com.Equipo2.RaceACar.DTO.ItemsDTO;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Categoria;
import com.Equipo2.RaceACar.model.Items;
import com.Equipo2.RaceACar.repository.CategoriaRepository;
import com.Equipo2.RaceACar.repository.ItemsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ItemsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemsRepository itemsRepository;

    public ItemsDTO crearItem(String nombre) {
        try {
            Items item = new Items();
            item.setNombre(nombre);
            Items itemGuardado = itemsRepository.save(item);
            return modelMapper.map(itemGuardado, ItemsDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo crear el item: " + e.getMessage());
        }
    }

    public void eliminarItem(Long itemId) {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el item con ID: " + itemId));

        itemsRepository.delete(item);
    }

    public ItemsDTO editarItem(Long itemId, String nuevoNombre) {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el item con ID: " + itemId));

        item.setNombre(nuevoNombre);
        Items itemActualizado = itemsRepository.save(item);

        return modelMapper.map(itemActualizado, ItemsDTO.class);
    }

    public List<ItemsDTO> obtenerTodosLosItems() {
        List<Items> items = itemsRepository.findAll();
        return items.stream()
                .map(item -> modelMapper.map(item, ItemsDTO.class))
                .collect(Collectors.toList());
    }

}