package com.Equipo2.RaceACar.DTO;

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
    private Boolean ocupado;
}
