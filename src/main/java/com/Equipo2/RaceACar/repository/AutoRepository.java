package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface AutoRepository extends JpaRepository<Auto, Long> {

    @Query("SELECT a FROM Auto a WHERE a.disponible = true " +
            "AND NOT EXISTS (SELECT r FROM Reserva r WHERE r.auto = a " +
            "AND (:fechaInicio BETWEEN r.fechaComienzo AND r.fechaFin " +
            "OR :fechaFin BETWEEN r.fechaComienzo AND r.fechaFin))")
    List<Auto> findDisponibleBetweenFechas(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
    List<Auto> findByDisponibleTrue();

}
