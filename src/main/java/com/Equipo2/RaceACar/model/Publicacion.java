package com.Equipo2.RaceACar.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Valor", nullable = false)

    private Integer valor;
    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;
    @Column(name = "Forma_de_pago", nullable = false)

    private String formaDePago;
//    private Usuario usuario;
    @OneToMany(mappedBy = "publicacion")
    private List<ItemsIncluidos> incluidos;
    @OneToMany(mappedBy = "publicacion")
    private List<ItemsExtras> extras;}
