package com.Equipo2.RaceACar.model;

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
@Table(name = "ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ciudad", nullable = false)
    private String ciudad;
    @Column(name = "pais", nullable = false)
    private String pais;

    @ManyToMany(mappedBy = "ubicaciones")
    private List<Auto> autos = new ArrayList<>();

}
