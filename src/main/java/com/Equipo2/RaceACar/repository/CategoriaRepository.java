package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
