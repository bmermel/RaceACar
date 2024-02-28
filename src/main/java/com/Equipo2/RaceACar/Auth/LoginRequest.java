package com.Equipo2.RaceACar.Auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String username;
    String password;
}
