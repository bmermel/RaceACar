package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.model.Categoria;
import com.Equipo2.RaceACar.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ItemsRepository extends JpaRepository<Items, Long> {
}
