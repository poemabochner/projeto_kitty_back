package com.kitty.projeto_kitty_back.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitty.projeto_kitty_back.domain.model.Lanche;
import com.kitty.projeto_kitty_back.domain.repository.LancheRepository;

@Service
public class LancheService {
  @Autowired
  LancheRepository lancheRepository;

  @Autowired
  IngredienteService ingredienteService;

  public List<Lanche> obterTodos() {
    return lancheRepository.findAll();
  }

  public Lanche buscaPorId(Long id) {
    Optional<Lanche> lancheEncontrado = lancheRepository.findById(id);
    if (lancheEncontrado.isEmpty()) {
      throw new LancheException("Não foi possível encontrar o lanche com id " + id + ".");
    }
    return lancheEncontrado.get();
  }

  // public Lanche atualizaLanche(Long id) {
  //   Lanche lancheExistente = getLancheById(id);
  // }

  static class LancheException extends RuntimeException {
    public LancheException(String message) {
      super(message);
    }
  }
}
