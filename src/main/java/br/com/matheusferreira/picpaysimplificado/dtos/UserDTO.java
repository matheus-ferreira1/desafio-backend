package br.com.matheusferreira.picpaysimplificado.dtos;

import java.math.BigDecimal;

import br.com.matheusferreira.picpaysimplificado.entities.UserType;

public record UserDTO(String firstName, String lastName, String document, String email, String password, BigDecimal balance, UserType userType) {
    
}
