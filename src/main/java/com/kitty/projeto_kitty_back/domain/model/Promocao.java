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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "promocao")

public class Promocao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_promocao")
  private Long idPromocao;

  @NotBlank
  @Column(name = "nome_promocao", nullable = false, length = 30, unique = true)
  private String nomePromocao;

  @NotBlank
  @Column(name = "descricao_promocao", nullable = false, length = 255, unique = false)
  private String descricaoPromocao;

  @NotBlank
  @Column(name = "preco_promocao", nullable = false, precision = 6, scale = 2, unique = false)
  private BigDecimal precoPromocao;

  @NotNull
  @Column(name = "desconto_promocao", nullable = false, unique = false)
  private Integer descontoPromocao;

  @ManyToMany
  @JoinTable(name = "promocao_lanche", joinColumns = @JoinColumn(name = "id_promocao"), inverseJoinColumns = @JoinColumn(name = "id_lanche"))
  private List<Lanche> lanches;

  public Promocao() {
  }

  public Promocao(Long idPromocao, @NotBlank String nomePromocao, @NotBlank String descricaoPromocao,
      @NotBlank BigDecimal precoPromocao, @NotNull Integer descontoPromocao, List<Lanche> lanches) {
    this.idPromocao = idPromocao;
    this.nomePromocao = nomePromocao;
    this.descricaoPromocao = descricaoPromocao;
    this.precoPromocao = precoPromocao;
    this.descontoPromocao = descontoPromocao;
    this.lanches = lanches;
  }

  public Long getIdPromocao() {
    return idPromocao;
  }

  public void setIdPromocao(Long idPromocao) {
    this.idPromocao = idPromocao;
  }

  public String getNomePromocao() {
    return nomePromocao;
  }

  public void setNomePromocao(String nomePromocao) {
    this.nomePromocao = nomePromocao;
  }

  public String getDescricaoPromocao() {
    return descricaoPromocao;
  }

  public void setDescricaoPromocao(String descricaoPromocao) {
    this.descricaoPromocao = descricaoPromocao;
  }

  public BigDecimal getPrecoPromocao() {
    return precoPromocao;
  }

  public void setPrecoPromocao(BigDecimal precoPromocao) {
    this.precoPromocao = precoPromocao;
  }

  public Integer getDescontoPromocao() {
    return descontoPromocao;
  }

  public void setDescontoPromocao(Integer descontoPromocao) {
    this.descontoPromocao = descontoPromocao;
  }

  public List<Lanche> getLanches() {
    return lanches;
  }

  public void setLanches(List<Lanche> lanches) {
    this.lanches = lanches;
  }
  
}
