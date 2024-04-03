package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.model.Auto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data

public class ReservaDTO {
    private Long id;

    private Auto auto;

    private LocalDate fechaComienzo;
    private LocalDate fechaFin;
    private String formaDePago;

    private String recogida;
    private String entrega;
}
