package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.repository.AutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutoService {

    @Autowired
    private AutoRepository repository;

    @Autowired
    private ModelMapper modelMapper;
    public void eliminarAuto(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("El auto con ID: " + id + " no existe");
        }
    }

    public void crearAuto(AutoDTO autoDTO) throws UnsupportedEncodingException {
        Auto auto = modelMapper.map(autoDTO, Auto.class);
        repository.save(auto);
    }

    public void editarAuto(Long id, AutoDTO autoDTO) {
        Optional<Auto> autoOptional = repository.findById(id);

        if (autoOptional.isPresent()) {
            Auto autoExistente = autoOptional.get();
            modelMapper.map(autoDTO, autoExistente);
            repository.save(autoExistente);
        } else {
            throw new NoSuchElementException("No se encontró el auto con ID: " + id);
        }
    }

    public List<AutoDTO> obtenerAutos (){
        List<Auto> autosList = repository.findAll();
        return autosList.stream()
                .map(auto -> modelMapper.map(auto, AutoDTO.class))
                .collect(Collectors.toList());
    }

}

