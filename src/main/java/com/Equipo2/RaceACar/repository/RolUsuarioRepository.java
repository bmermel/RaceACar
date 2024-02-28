package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.model.Reserva;
import com.Equipo2.RaceACar.model.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolUsuarioRepository  extends JpaRepository<RolUsuario,Long> {
}
