package com.Equipo2.RaceACar.model;

import com.Equipo2.RaceACar.model.Enums.Combustion;
import com.Equipo2.RaceACar.model.Enums.TipoDeCaja;
import com.Equipo2.RaceACar.model.Enums.Traccion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

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
    @Column(name = "Tipo_de_combustion", nullable = false)

    private Combustion combustion;
    @Column(name = "disponible", nullable = false)

    private Boolean disponible;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "images")
    private Collection<String> images = new ArrayList<>();

    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "auto_items",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Items> items = new ArrayList<>();

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