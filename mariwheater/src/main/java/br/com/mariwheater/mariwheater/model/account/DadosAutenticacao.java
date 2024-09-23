package br.com.mariwheater.mariwheater.model.account;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao (

        @NotBlank
        String login,

        @NotBlank
        String password
){
}
