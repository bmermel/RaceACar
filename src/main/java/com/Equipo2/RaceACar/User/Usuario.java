package com.Equipo2.RaceACar.User;

import com.Equipo2.RaceACar.model.RolUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Usuario  implements UserDetails {

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
    @JoinColumn(name = "rolUsuario_id",nullable = true)
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RolUsuario rolUsuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolUsuario.getRol().getAuthorities();
    }

    @Override
    public String getUsername() {
        return nombreCompleto;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
