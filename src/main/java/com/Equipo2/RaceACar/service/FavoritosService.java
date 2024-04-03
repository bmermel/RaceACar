package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Favoritos;
import com.Equipo2.RaceACar.repository.CategoriaRepository;
import com.Equipo2.RaceACar.repository.FavoritosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritosService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FavoritosRepository favoritosRepository;
    public ResponseEntity<String> agregarAFavoritos(Usuario usuario, Auto auto) {
        try {
            if (!favoritosRepository.existsByUsuarioAndAuto(usuario, auto)) {
                Favoritos favorito = new Favoritos();
                favorito.setUsuario(usuario);
                favorito.setAuto(auto);
                favoritosRepository.save(favorito);
                return ResponseEntity.ok("El auto se agregó a favoritos correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El auto ya está en la lista de favoritos.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al agregar el auto a favoritos.");
        }
    }

    public ResponseEntity<String> eliminarDeFavoritos(Usuario usuario, Auto auto) {
        try {
            if (favoritosRepository.existsByUsuarioAndAuto(usuario, auto)) {
                favoritosRepository.deleteByUsuarioAndAuto(usuario, auto);
                return ResponseEntity.ok("El auto se eliminó de favoritos correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El auto no está en la lista de favoritos.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al eliminar el auto de favoritos.");
        }
    }

    public ResponseEntity<?> obtenerFavoritosPorUsuarioId(Long usuarioId) {
        try {
            List<Favoritos> favoritos = favoritosRepository.findAllByUsuarioId(usuarioId);
            if (favoritos.isEmpty()) {
                return ResponseEntity.ok("No se encontraron favoritos para el usuario.");
            } else {
                return ResponseEntity.ok(favoritos);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}