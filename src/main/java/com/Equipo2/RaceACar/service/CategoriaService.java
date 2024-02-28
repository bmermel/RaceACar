package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.CategoriaDTO;
import com.Equipo2.RaceACar.model.Categoria;
import com.Equipo2.RaceACar.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setCategoria(categoriaDTO.getCategoria());
        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        CategoriaDTO nuevaCategoriaDTO = new CategoriaDTO();
        nuevaCategoriaDTO.setCategoria(categoriaGuardada.getCategoria());

        return nuevaCategoriaDTO;

    }

}