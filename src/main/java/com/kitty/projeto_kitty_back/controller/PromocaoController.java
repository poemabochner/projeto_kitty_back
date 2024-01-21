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

import com.kitty.projeto_kitty_back.domain.model.Promocao;
import com.kitty.projeto_kitty_back.domain.service.PromocaoService;

@RestController
@RequestMapping("/api/promocao")
@CrossOrigin
public class PromocaoController {

  @Autowired
  PromocaoService promocaoService;

  @GetMapping
  public List<Promocao> obterTodosPromocoes() {
    return promocaoService.obterTodos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Promocao> buscaPorIdPromocao(@PathVariable Long id) {
    Promocao promocaoEncontrada = promocaoService.buscaPorId(id);
    return ResponseEntity.ok(promocaoEncontrada);
  }

  @PostMapping
  public ResponseEntity<Promocao> cadastraPromocao(@RequestBody Promocao promocao) {
    Promocao promocaoCadastrada = promocaoService.cadastraPromocao(promocao);
    return ResponseEntity.status(HttpStatus.CREATED).body(promocaoCadastrada);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Promocao> atualizaPromocao(@PathVariable Long id, @RequestBody Promocao promocao) {
    Promocao promocaoEditada = promocaoService.atualizaPromocao(id, promocao);
    return ResponseEntity.ok(promocaoEditada);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletaPromocao(@PathVariable Long id) {
    promocaoService.deletaPromocao(id);
    return ResponseEntity.noContent().build();
  }
}