package com.kitty.projeto_kitty_back.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ingrediente")

public class Ingrediente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ingrediente")
  private Long idIngrediente;

  @NotBlank
  @Column(name = "nome_ingrediente", nullable = false, length = 30, unique = true)
  private String nomeIngrediente;

  @NotBlank
  @Column(name = "preco_ingrediente", nullable = false, precision = 6, scale = 2, unique = false)
  private BigDecimal precoIngrediente;

  public Ingrediente() {
  }

  public Ingrediente(Long idIngrediente, @NotBlank String nomeIngrediente, @NotBlank BigDecimal precoIngrediente) {
    this.idIngrediente = idIngrediente;
    this.nomeIngrediente = nomeIngrediente;
    this.precoIngrediente = precoIngrediente;
  }

  public Long getIdIngrediente() {
    return idIngrediente;
  }

  public void setIdIngrediente(Long idIngrediente) {
    this.idIngrediente = idIngrediente;
  }

  public String getNomeIngrediente() {
    return nomeIngrediente;
  }

  public void setNomeIngrediente(String nomeIngrediente) {
    this.nomeIngrediente = nomeIngrediente;
  }

  public BigDecimal getPrecoIngrediente() {
    return precoIngrediente;
  }

  public void setPrecoIngrediente(BigDecimal precoIngrediente) {
    this.precoIngrediente = precoIngrediente;
  }
}

