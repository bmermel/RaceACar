package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.CategoriaConIdDTO;
import com.Equipo2.RaceACar.DTO.CategoriaDTO;
import com.Equipo2.RaceACar.DTO.ReservaDTO;
import com.Equipo2.RaceACar.model.Categoria;
import com.Equipo2.RaceACar.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
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

    public void eliminarCategoria(Long id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No se encontró ninguna categoría con el ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al eliminar la categoría con el ID: " + id, e);
        }
    }
    public CategoriaDTO editarCategoria(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró ninguna categoría con el ID: " + id));

        categoriaExistente.setCategoria(categoriaDTO.getCategoria());

        Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);

        // Convertir la categoría actualizada a DTO y devolverla
        CategoriaDTO categoriaActualizadaDTO = new CategoriaDTO();
        categoriaActualizadaDTO.setCategoria(categoriaActualizada.getCategoria());
        return categoriaActualizadaDTO;
    }

    public List<CategoriaConIdDTO> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaConIdDTO.class))
                .collect(Collectors.toList());
    }

}