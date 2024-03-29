package com.example.exchangeRate.security.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private final String type = "Bearer";

    private String token;

    private Long id;

    private String username;

    private String email;

    private List<String> roles;

}
