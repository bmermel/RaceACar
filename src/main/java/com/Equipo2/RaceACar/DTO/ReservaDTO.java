package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.model.Auto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
@Data

public class ReservaDTO {
    private Long id;

    private Auto auto;

    private LocalDate fechaComienzo;
    private LocalDate fechaFin;
}
