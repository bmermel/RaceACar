package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.CrearAutoDTO;
import com.Equipo2.RaceACar.Exceptions.MailSendingException;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Categoria;
import com.Equipo2.RaceACar.repository.AutoRepository;
import com.Equipo2.RaceACar.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutoService {

    @Autowired
    private AutoRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;
    public void eliminarAuto(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("El auto con ID: " + id + " no existe");
        }
    }

    public void crearAuto(CrearAutoDTO autoDTO) throws UnsupportedEncodingException {
        Auto auto = modelMapper.map(autoDTO, Auto.class);
        repository.save(auto);
    }

    public void editarAuto(Long id, AutoDTO autoDTO) {
        Optional<Auto> autoOptional = repository.findById(id);

        if (autoOptional.isPresent()) {
            Auto autoExistente = autoOptional.get();
            //modelMapper.map(autoDTO, autoExistente);

            Categoria categoria = categoriaRepository.findById(autoDTO.getIdCategoria()).orElse(null);

            autoExistente.setMarca(autoDTO.getMarca());
            autoExistente.setModelo(autoDTO.getModelo());
            autoExistente.setAnio(autoDTO.getAnio());
            autoExistente.setCapacidad(autoDTO.getCapacidad());
            autoExistente.setColor(autoDTO.getColor());
            autoExistente.setImages(autoDTO.getImages());
            autoExistente.setItems(autoDTO.getItems());
            autoExistente.setCategoria(categoria);
            autoExistente.setCaballosDeFuerza(autoDTO.getCaballosDeFuerza());
            autoExistente.setTipoDeCaja(autoDTO.getTipoDeCaja());
            autoExistente.setValor(autoDTO.getValor());
            autoExistente.setCombustion(autoDTO.getCombustion());

            repository.save(autoExistente);
        } else {
            throw new NoSuchElementException("No se encontró el auto con ID: " + id);
        }
    }

    public List<AutoDTO> obtenerAutos (){
        List<Auto> autosList = repository.findAll();

        if (autosList.isEmpty()) {
            System.out.println("La lista está aún vacía");
            return Collections.emptyList(); // Devuelve una lista vacía
        } else {
            return autosList.stream()
                    .map(auto -> modelMapper.map(auto, AutoDTO.class))
                    .collect(Collectors.toList());
        }
    }
    public List<Auto> findAutosDisponiblesEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return repository.findDisponibleBetweenFechas(fechaInicio, fechaFin);
    }
    public List<Auto> obtenerAutosDisponibles() {
        List<Auto> autosDisponibles = repository.findByDisponibleTrue();
        if (autosDisponibles.isEmpty()) {
            throw new MailSendingException.ResourceNotFoundException("No se encontraron autos disponibles");
        }
        return autosDisponibles;
    }
}

