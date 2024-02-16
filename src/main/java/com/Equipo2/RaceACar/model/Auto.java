package com.Equipo2.RaceACar.model;

import com.Equipo2.RaceACar.DTO.TipoDeCaja;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;


    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "anio", nullable = false)
    private int anio;
    @Column(name = "capacidad", nullable = false)
    private int capacidad;
    @Column(name = "Tipo de caja", nullable = false)

    private TipoDeCaja tipoDeCaja;
    @Column(name = "Auto Ocupado", nullable = false)

    private Boolean ocupado;
}
