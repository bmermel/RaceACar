package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AutoRepository extends JpaRepository<Auto, Long> {
}
