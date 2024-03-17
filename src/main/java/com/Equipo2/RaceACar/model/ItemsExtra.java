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
@Table(name = "Items_extra")
public class ItemsExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "precio", nullable = false)
    private Integer precio;
    @ManyToMany(mappedBy = "itemsExtras")
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();


}


