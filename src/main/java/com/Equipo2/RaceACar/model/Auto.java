package com.Equipo2.RaceACar.model;

import com.Equipo2.RaceACar.model.Enums.TipoDeAuto;
import com.Equipo2.RaceACar.model.Enums.TipoDeCaja;
import com.Equipo2.RaceACar.model.Enums.Traccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "Caballos de fuerza", nullable = false)
    private Integer caballosDeFuerza;
    @Column(name = "Tracción", nullable = false)

    private Traccion traccion;
    @Column(name = "Tipo de Auto", nullable = false)

    private TipoDeAuto tipoDeAuto;
    @Column(name = "Auto disponible", nullable = false)

    private Boolean disponible;


    @ManyToMany
    @JoinTable(
            name = "auto_ubicacion",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "ubicacion_id")
    )
    private List<Ubicacion> ubicaciones = new ArrayList<>();
/*    @ManyToMany
    @JoinTable(
            name = "auto_ubicacion",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "ubicacion_id")
    )
    private Set<Ubicacion> ubicaciones = new HashSet<>();*/

}
