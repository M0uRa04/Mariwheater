package br.com.mariwheater.mariwheater.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataAccount (

    @NotBlank
    String name,

    @Email
    String email,

    @NotBlank
    String cityName
){
}
