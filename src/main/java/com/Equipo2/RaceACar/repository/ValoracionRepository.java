package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    @Query("SELECT AVG(v.valoracion) FROM Valoracion v WHERE v.reserva.auto.id = :autoId")
    Double obtenerPromedioValoracionPorAutoId(Long autoId);

    @Query("SELECT v FROM Valoracion v WHERE v.reserva.auto.id = :autoId")
    List<Valoracion> findByReservaAutoId(Long autoId);
}
