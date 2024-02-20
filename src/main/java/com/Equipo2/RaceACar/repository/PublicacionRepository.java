package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository  extends JpaRepository<Publicacion, Long> {
}
