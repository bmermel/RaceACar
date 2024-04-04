package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Valoracion;
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

    private Double total;
    private UsuarioSinPassDTO usuario;
    private ValoracionDTO valoracion;
}
