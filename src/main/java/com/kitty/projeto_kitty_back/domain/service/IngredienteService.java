package com.kitty.projeto_kitty_back.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.kitty.projeto_kitty_back.domain.model.Ingrediente;
import com.kitty.projeto_kitty_back.domain.repository.IngredienteRepository;

public class IngredienteService {
  @Autowired
  private IngredienteRepository ingredienteRepository;

  public List<Ingrediente> obterTodos() {

    return ingredienteRepository.findAll();
  }

  public Ingrediente buscaPorId(Long id) {
    Optional<Ingrediente> ingredienteOptional = ingredienteRepository.findById(id);

    return ingredienteOptional
        .orElseThrow(() -> new IngredienteException("Ingrediente não encontrado com o ID: " + id));
  }

  public Ingrediente criaIngrediente(Ingrediente ingrediente) {
    if (ingredienteRepository.existsByNomeIngrediente(ingrediente.getNomeIngrediente())) {
      throw new IngredienteException(
          "Já existe um ingrediente com o nome: " + ingrediente.getNomeIngrediente());
    }
    if (ingrediente.getPrecoIngrediente() == null
        || ingrediente.getPrecoIngrediente().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IngredienteException("O preço do ingrediente deve ser maior que zero.");
    }
    return ingredienteRepository.save(ingrediente);
  }

  public Ingrediente atualizaIngrediente(Long id, Ingrediente ingrediente) {
    Ingrediente ingredienteEncontrado = buscaPorId(id);

    if (!ingredienteEncontrado.getNomeIngrediente().equals(ingrediente.getNomeIngrediente()) &&
        ingredienteRepository.existsByNomeIngrediente(ingrediente.getNomeIngrediente())) {
      throw new IngredienteException("Já existe um ingrediente com o nome: " + ingrediente.getNomeIngrediente());
    }

    ingredienteEncontrado.setNomeIngrediente(ingrediente.getNomeIngrediente());
    ingredienteEncontrado.setPrecoIngrediente(ingrediente.getPrecoIngrediente());

    return ingredienteRepository.save(ingredienteEncontrado);
  }

  public void deletaIngrediente(Long id) {
    buscaPorId(id);
    ingredienteRepository.deleteById(id);
  }

  static class IngredienteException extends RuntimeException {
    public IngredienteException(String message) {
      super(message);
    }
  }

}