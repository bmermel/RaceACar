package com.Equipo2.RaceACar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "item_incluido", nullable = false)
    private Boolean incluido;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private List<Auto> autos = new ArrayList<>();


}


