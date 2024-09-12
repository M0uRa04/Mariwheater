package br.com.mariwheater.mariwheater.controller;

import br.com.mariwheater.mariwheater.dto.DataAccount;
import br.com.mariwheater.mariwheater.service.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping("/all")
    public ResponseEntity getAllAccount () {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity getAccountByCityName (@PathVariable String cityName) {
        return ResponseEntity.ok(accountService.getAccountByCityName(cityName));
    }

    @GetMapping("/{id}")
    public ResponseEntity findAccountById (@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateAccount (@PathVariable Long id, @RequestBody @Valid  DataAccount dataAccount ) {
        var foundAccount = accountService.getAccount(id);
        var updatedAccount = accountService.updateAccount(foundAccount, dataAccount);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount (@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }


}
