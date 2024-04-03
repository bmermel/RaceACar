package com.Equipo2.RaceACar.controller;


import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.repository.AutoRepository;
import com.Equipo2.RaceACar.service.AutoService;
import com.Equipo2.RaceACar.service.FavoritosService;
import com.Equipo2.RaceACar.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favoritos")
@CrossOrigin(origins = "*")
public class FavoritosController {

    @Autowired
    private FavoritosService favoritosService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AutoService autoService;
    @Autowired
    private AutoRepository autoRepository;

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarAFavoritos(@RequestParam Long idUsuario, @RequestParam Long idAuto) {
        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
            }
            Auto auto = autoService.obtenerAutoPorId(idAuto);
            if (auto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El auto no fue encontrado.");
            }
            return favoritosService.agregarAFavoritos(usuario, auto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar favorito.");
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarDeFavoritos(@RequestParam Long idUsuario, @RequestParam Long idAuto) {
        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
            }
            Auto auto = autoService.obtenerAutoPorId(idAuto);
            if (auto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El auto no fue encontrado.");
            }
            return favoritosService.eliminarDeFavoritos(usuario, auto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar favorito.");
        }
    }
    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> obtenerFavoritosPorUsuarioId(@PathVariable Long usuarioId) {
        try {
            return favoritosService.obtenerFavoritosPorUsuarioId(usuarioId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener favoritos.");
        }
    }
}

