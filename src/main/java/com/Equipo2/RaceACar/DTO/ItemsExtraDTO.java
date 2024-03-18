package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.model.Reserva;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ItemsExtraDTO {

    private Long id;
    private String nombre;
    private Integer precio;
    private List<Reserva> reservas = new ArrayList<>();
}
