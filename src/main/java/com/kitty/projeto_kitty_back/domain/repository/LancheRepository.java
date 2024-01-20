package com.kitty.projeto_kitty_back.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitty.projeto_kitty_back.domain.model.Lanche;

public interface LancheRepository extends JpaRepository<Lanche, Long>{
  
}
