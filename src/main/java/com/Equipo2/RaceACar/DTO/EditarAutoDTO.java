package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.model.Enums.Combustion;
import com.Equipo2.RaceACar.model.Enums.TipoDeCaja;
import com.Equipo2.RaceACar.model.Enums.Traccion;
import com.Equipo2.RaceACar.model.Items;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditarAutoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String color;
    private Long idCategoria;
    private int anio;
    private int capacidad;
    private TipoDeCaja tipoDeCaja;
    private Integer caballosDeFuerza;
    private Traccion traccion;
    private Combustion combustion;
    private Double valor;
    private Boolean disponible;
}