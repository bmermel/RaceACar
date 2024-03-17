package com.Equipo2.RaceACar.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;
    @Column(name = "Fecha_comienzo", nullable = false)
    private LocalDate fechaComienzo;
    @Column(name = "Fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "Forma_de_pago", nullable = false)
    private String formaDePago;
    @Column(name = "valoracion")
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Valoracion> valoraciones = new ArrayList<>();

}
