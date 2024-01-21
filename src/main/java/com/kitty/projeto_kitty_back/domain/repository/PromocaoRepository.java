package com.kitty.projeto_kitty_back.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitty.projeto_kitty_back.domain.model.Promocao;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

  boolean existsByNomePromocao(String nomePromocao);
  
}
