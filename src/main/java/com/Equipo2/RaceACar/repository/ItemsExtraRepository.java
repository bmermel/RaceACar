package com.Equipo2.RaceACar.repository;

import com.Equipo2.RaceACar.model.Items;
import com.Equipo2.RaceACar.model.ItemsExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ItemsExtraRepository extends JpaRepository<ItemsExtra, Long> {
}
