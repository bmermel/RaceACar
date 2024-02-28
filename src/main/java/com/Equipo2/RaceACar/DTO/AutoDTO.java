package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.model.Enums.Combustion;
import com.Equipo2.RaceACar.model.Enums.TipoDeCaja;
import com.Equipo2.RaceACar.model.Enums.Traccion;
import lombok.Data;

@Data
public class AutoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String color;
    private int anio;
    private int capacidad;
    private TipoDeCaja tipoDeCaja;
    private Integer caballosDeFuerza;
    private Traccion traccion;
    private Combustion combustion;
    private Boolean disponible;
}