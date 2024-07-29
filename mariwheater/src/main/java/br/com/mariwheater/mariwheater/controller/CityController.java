package br.com.mariwheater.mariwheater.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @GetMapping
    public ResponseEntity testandoController () {
        return ResponseEntity.ok("O teste foi concluído com sucesso");
    }
}
