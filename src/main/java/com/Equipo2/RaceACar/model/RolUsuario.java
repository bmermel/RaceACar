package com.Equipo2.RaceACar.model;

import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.User.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "rolUsuario")
public class RolUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles rol;

    @JsonIgnore
    @OneToMany(mappedBy = "rolUsuario")
    private List<Usuario> usuarios;
}
