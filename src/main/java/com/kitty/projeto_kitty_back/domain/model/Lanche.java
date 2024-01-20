package com.kitty.projeto_kitty_back.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "lanche")

public class Lanche {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_lanche")
  private Long idLanche;

  @NotBlank
  @Column(name = "nome_lanche", nullable = false, length = 30, unique = true)
  private String nomeLanche;

  @NotBlank
  @Column(name = "descricao_lanche", nullable = false, length = 255, unique = false)
  private String descricaoLanche;

  @NotBlank
  @Column(name = "preco_lanche", nullable = false, precision = 6, scale = 2, unique = false)
  private BigDecimal precoLanche;

  @ManyToMany
  @JoinTable(name = "lanche_ingrediente", joinColumns = @JoinColumn(name = "id_lanche"), inverseJoinColumns = @JoinColumn(name = "id_ingrediente"))
  private List<Ingrediente> ingredientes;

  public Lanche(Long idLanche, @NotBlank String nomeLanche, @NotBlank String descricaoLanche,
      @NotBlank BigDecimal precoLanche, List<Ingrediente> ingredientes) {
    this.idLanche = idLanche;
    this.nomeLanche = nomeLanche;
    this.descricaoLanche = descricaoLanche;
    this.precoLanche = precoLanche;
    this.ingredientes = ingredientes;
  }

  public Lanche() {
  }

  public Long getIdLanche() {
    return idLanche;
  }

  public void setIdLanche(Long idLanche) {
    this.idLanche = idLanche;
  }

  public String getNomeLanche() {
    return nomeLanche;
  }

  public void setNomeLanche(String nomeLanche) {
    this.nomeLanche = nomeLanche;
  }

  public String getDescricaoLanche() {
    return descricaoLanche;
  }

  public void setDescricaoLanche(String descricaoLanche) {
    this.descricaoLanche = descricaoLanche;
  }

  public BigDecimal getPrecoLanche() {
    return precoLanche;
  }

  public void setPrecoLanche(BigDecimal precoLanche) {
    this.precoLanche = precoLanche;
  }

  public List<Ingrediente> getIngredientes() {
    return ingredientes;
  }

  public void setIngredientes(List<Ingrediente> ingredientes) {
    this.ingredientes = ingredientes;
  }
  
}