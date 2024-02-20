package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.model.Enums.TipoDeAuto;
import com.Equipo2.RaceACar.model.Enums.TipoDeCaja;
import com.Equipo2.RaceACar.model.Enums.Traccion;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private TipoDeAuto tipoDeAuto;
    private Boolean ocupado;
}