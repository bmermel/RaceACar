package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.PublicacionDTO;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Publicacion;
import com.Equipo2.RaceACar.repository.AutoRepository;
import com.Equipo2.RaceACar.repository.PublicacionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository repository;

    @Autowired
    private ModelMapper modelMapper;



    public void eliminarPublicación(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("La publicación con ID: " + id + " no existe");
        }
    }

    public void crearPublicación(PublicacionDTO publicacionDTO) throws UnsupportedEncodingException {
        Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);
        repository.save(publicacion);
    }

    public void editarAuto(Long id, PublicacionDTO publicacionDTO) {
        Optional<Publicacion> publicacionOptional = repository.findById(id);

        if (publicacionOptional.isPresent()) {
            Publicacion publicacionExistente = publicacionOptional.get();
            modelMapper.map(publicacionDTO, publicacionExistente);
            repository.save(publicacionExistente);
        } else {
            throw new NoSuchElementException("No se encontró la publicación con ID: " + id);
        }
    }

    public List<PublicacionDTO> obtenerAutos (){
        List<Publicacion> publicacionList = repository.findAll();

        if (publicacionList.isEmpty()) {
            System.out.println("La lista está aún vacía");
            return Collections.emptyList(); // Devuelve una lista vacía
        } else {
            return publicacionList.stream()
                    .map(publicacion -> modelMapper.map(publicacion, PublicacionDTO.class))
                    .collect(Collectors.toList());
        }
    }
}
