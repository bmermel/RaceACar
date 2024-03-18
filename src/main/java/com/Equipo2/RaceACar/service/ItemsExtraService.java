package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.CategoriaConIdDTO;
import com.Equipo2.RaceACar.DTO.CategoriaDTO;
import com.Equipo2.RaceACar.DTO.ItemsDTO;
import com.Equipo2.RaceACar.DTO.ItemsExtraDTO;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Categoria;
import com.Equipo2.RaceACar.model.Items;
import com.Equipo2.RaceACar.model.ItemsExtra;
import com.Equipo2.RaceACar.repository.CategoriaRepository;
import com.Equipo2.RaceACar.repository.ItemsExtraRepository;
import com.Equipo2.RaceACar.repository.ItemsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ItemsExtraService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemsExtraRepository itemsExtraRepository;

    public ItemsExtraDTO crearItemExtra(String nombre, Integer precio) {
        try {
            ItemsExtra itemExtra = new ItemsExtra();
            itemExtra.setNombre(nombre);
            itemExtra.setPrecio(precio);
            ItemsExtra itemExtraGuardado = itemsExtraRepository.save(itemExtra);
            return modelMapper.map(itemExtraGuardado, ItemsExtraDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo crear el item extra: " + e.getMessage());
        }
    }

    public void eliminarItemExtra(Long itemExtraId) {
        ItemsExtra itemExtra = itemsExtraRepository.findById(itemExtraId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el item extra con ID: " + itemExtraId));

        itemsExtraRepository.delete(itemExtra);
    }

    public ItemsExtraDTO editarItemExtra(Long itemExtraId, String nuevoNombre, Integer nuevoPrecio) {
        ItemsExtra itemExtra = itemsExtraRepository.findById(itemExtraId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el item extra con ID: " + itemExtraId));

        itemExtra.setNombre(nuevoNombre);
        itemExtra.setPrecio(nuevoPrecio);
        ItemsExtra itemExtraActualizado = itemsExtraRepository.save(itemExtra);

        return modelMapper.map(itemExtraActualizado, ItemsExtraDTO.class);
    }

    public List<ItemsExtraDTO> obtenerTodosLosItemsExtra() {
        List<ItemsExtra> itemsExtra = itemsExtraRepository.findAll();
        return itemsExtra.stream()
                .map(itemExtra -> modelMapper.map(itemExtra, ItemsExtraDTO.class))
                .collect(Collectors.toList());
    }
}