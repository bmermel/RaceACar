package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findByAuto(Auto auto);
    List<Reserva> findByFechaFinBefore(LocalDate fecha);

    List<Reserva> findByUsuario(Usuario usuario);

    Reserva findFirstByUsuarioOrderByFechaComienzoDesc(Usuario usuario);


}
