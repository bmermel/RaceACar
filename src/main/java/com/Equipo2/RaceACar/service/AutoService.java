package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.CrearAutoDTO;
import com.Equipo2.RaceACar.DTO.EditarAutoDTO;
import com.Equipo2.RaceACar.Exceptions.MailSendingException;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Categoria;
import com.Equipo2.RaceACar.model.Reserva;
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
        Categoria categoria = categoriaRepository.findById(autoDTO.getIdCategoria())
                .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + autoDTO.getIdCategoria()));

        Auto auto = modelMapper.map(autoDTO, Auto.class);
        auto.setCategoria(categoria);
        repository.save(auto);
    }

    public void editarAuto(Long id, EditarAutoDTO autoDTO) {
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
            autoExistente.setCategoria(categoria);
            autoExistente.setCaballosDeFuerza(autoDTO.getCaballosDeFuerza());
            autoExistente.setTipoDeCaja(autoDTO.getTipoDeCaja());
            autoExistente.setValor(autoDTO.getValor());
            autoExistente.setCombustion(autoDTO.getCombustion());
            autoExistente.setDisponible(autoDTO.getDisponible());

            repository.save(autoExistente);
        } else {
            throw new NoSuchElementException("No se encontró el auto con ID: " + id);
        }
    }

    public List<Auto> obtenerAutos (){
        return repository.findAll();

    }
    public List<Auto> findAutosDisponiblesEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return repository.findDisponibleBetweenFechas(fechaInicio, fechaFin);
    }

    public void toggleDisponibilidad(Long id){
        Auto auto = repository.findById(id).orElse(null);
        auto.setDisponible(!auto.getDisponible());

        repository.save(auto);
    }
    public List<Auto> obtenerAutosDisponibles() {
        List<Auto> autosDisponibles = repository.findByDisponibleTrue();
        if (autosDisponibles.isEmpty()) {
            throw new MailSendingException.ResourceNotFoundException("No se encontraron autos disponibles");
        }
        return autosDisponibles;
    }

    public List<Auto> obtenerAutosDisponiblesEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        // Obtener todos los autos disponibles
        List<Auto> autosDisponibles = repository.findByDisponibleTrue();

        // Filtrar los autos que están disponibles dentro del rango de fechas
        autosDisponibles = autosDisponibles.stream()
                .filter(auto -> estaDisponibleEnFechas(auto, fechaInicio, fechaFin))
                .collect(Collectors.toList());

        if (autosDisponibles.isEmpty()) {
            throw new MailSendingException.ResourceNotFoundException("No se encontraron autos disponibles para las fechas especificadas");
        }

        return autosDisponibles;
    }

    private boolean estaDisponibleEnFechas(Auto auto, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reserva> reservas = auto.getReservas();

        for (Reserva reserva : reservas) {
            if (reserva.getFechaComienzo().isBefore(fechaFin) &&
                    reserva.getFechaFin().isAfter(fechaInicio)) {
                return false; // El auto está reservado en parte del rango de fechas
            }
        }

        return true;
    }
}

