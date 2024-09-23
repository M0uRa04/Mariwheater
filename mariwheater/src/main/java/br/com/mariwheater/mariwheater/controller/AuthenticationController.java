package br.com.mariwheater.mariwheater.controller;

import br.com.mariwheater.mariwheater.model.account.Account;
import br.com.mariwheater.mariwheater.model.account.DadosAutenticacao;
import br.com.mariwheater.mariwheater.security.DadosTokenJWT;
import br.com.mariwheater.mariwheater.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin (@Valid @RequestBody DadosAutenticacao dadosAutenticacao) {
        var token = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.password());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Account) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
