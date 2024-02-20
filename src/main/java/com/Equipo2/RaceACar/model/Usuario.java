package com.Equipo2.RaceACar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty
    private String password;
    @Column(name = "telefono", nullable = false)
    @JsonProperty
    private String telefono;
    @Column(name = "documento", nullable = false)
    @JsonProperty
    private String documento;




    @JoinColumn(name = "rolUsuario_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RolUsuario rolUsuario;
}
