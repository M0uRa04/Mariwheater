package br.com.mariwheater.mariwheater.controller;

import br.com.mariwheater.mariwheater.DTO.DataAccount;
import br.com.mariwheater.mariwheater.service.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity createAccount (@RequestBody @Valid DataAccount dataAccount) {
        accountService.createAndSaveAccount(dataAccount);
        var uri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri();
        return ResponseEntity.created(uri).body(dataAccount);
    }
}
