package com.kitty.projeto_kitty_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitty.projeto_kitty_back.domain.model.Ingrediente;
import com.kitty.projeto_kitty_back.domain.service.IngredienteService;

@RestController
@RequestMapping("/api/ingrediente")
@CrossOrigin

public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public List<Ingrediente> obterTodosIngredientes() {
        return ingredienteService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> buscaPorIdIngrediente(@PathVariable Long id) {
        Ingrediente ingredienteEncontrado = ingredienteService.buscaPorId(id);
        return ResponseEntity.ok(ingredienteEncontrado);
    }

    @PostMapping
    public ResponseEntity<Ingrediente> cadastraIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente ingredienteCadastrado = ingredienteService.cadastraIngrediente(ingrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> atualizaIngrediente(@PathVariable Long id,
            @RequestBody Ingrediente ingrediente) {
        Ingrediente ingredienteEditado = ingredienteService.atualizaIngrediente(id, ingrediente);
        return ResponseEntity.ok(ingredienteEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaIngrediente(@PathVariable Long id) {
        ingredienteService.deletaIngrediente(id);
        return ResponseEntity.noContent().build();
    }
}
