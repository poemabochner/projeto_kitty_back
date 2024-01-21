package com.kitty.projeto_kitty_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitty.projeto_kitty_back.domain.model.Lanche;
import com.kitty.projeto_kitty_back.domain.service.LancheService;

@RestController
@RequestMapping("/api/lanche")
@CrossOrigin
public class LancheController {

  @Autowired
  private LancheService lancheService;

  @GetMapping
  public List<Lanche> obterTodosLanches() {
    return lancheService.obterTodos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Lanche> buscaPorIdLanche(@PathVariable Long id) {
    Lanche lancheEncontrado = lancheService.buscaPorId(id);
    return ResponseEntity.ok(lancheEncontrado);
  }

  // @PostMapping
  // public ResponseEntity<Lanche> cadastraLanche(@RequestBody Lanche lanche) {
  // Lanche lancheCadastrado = lancheService.cadastraLanche(lanche);
  // return ResponseEntity.status(HttpStatus.CREATED).body(lancheCadastrado);
  // }

  // @PutMapping("/{id}")
  // public ResponseEntity<Lanche> atualizaLanche(@PathVariable Long id, @RequestBody Lanche lanche) {
  //   Lanche lancheEditado = lancheService.atualizaLanche(id, lanche);
  //   return ResponseEntity.ok(lancheEditado);
  // }
}
