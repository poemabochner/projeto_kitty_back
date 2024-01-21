package com.kitty.projeto_kitty_back.domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitty.projeto_kitty_back.domain.model.Ingrediente;
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

  public Lanche cadastraLanche(Lanche lanche) {
    BigDecimal precoLanche = BigDecimal.ZERO;
    List<Ingrediente> ingredientesLanche = new ArrayList<>();

    for (Ingrediente ingrediente : lanche.getIngredientes()) {
      ingredientesLanche.add(ingredienteService.buscaPorId(ingrediente.getIdIngrediente()));
    }
    lanche.setIngredientes(ingredientesLanche);

    for (Ingrediente ingrediente : lanche.getIngredientes()) {
      precoLanche = precoLanche.add(ingrediente.getPrecoIngrediente());
    }
    lanche.setPrecoLanche(precoLanche);

    return lancheRepository.save(lanche);

  }

  // public Lanche atualizaLanche(Long id) {
  // Lanche lancheExistente = getLancheById(id);
  // }

  static class LancheException extends RuntimeException {
    public LancheException(String message) {
      super(message);
    }
  }
}
