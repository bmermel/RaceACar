package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FavoritosRepository extends JpaRepository<Favoritos, Long> {
    boolean existsByUsuarioAndAuto(Usuario usuario, Auto auto);
    void deleteByUsuarioAndAuto(Usuario usuario, Auto auto);
    List<Favoritos> findAllByUsuarioId(Long usuarioId);


}
