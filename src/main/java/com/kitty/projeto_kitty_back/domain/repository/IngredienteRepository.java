package com.kitty.projeto_kitty_back.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitty.projeto_kitty_back.domain.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
  
}
