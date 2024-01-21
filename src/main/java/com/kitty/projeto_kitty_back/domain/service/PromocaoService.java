package com.kitty.projeto_kitty_back.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitty.projeto_kitty_back.domain.model.Lanche;
import com.kitty.projeto_kitty_back.domain.model.Promocao;
import com.kitty.projeto_kitty_back.domain.repository.PromocaoRepository;

@Service
public class PromocaoService {
  @Autowired
  PromocaoRepository promocaoRepository;

  @Autowired
  LancheService lancheService;

  public List<Promocao> obterTodos() {
    return promocaoRepository.findAll();
  }

  public Promocao buscaPorId(Long id) {
    Optional<Promocao> promocaoEncontrada = promocaoRepository.findById(id);
    if (promocaoEncontrada.isEmpty()) {
      throw new PromocaoException("Não foi possível encontrar o promoção com id " + id + ".");
    }
    return promocaoEncontrada.get();
  }

  public Promocao cadastraPromocao(Promocao promocao) {
    BigDecimal precoPromocao = BigDecimal.ZERO;
    List<Lanche> lanchesPromocao = new ArrayList<>();

    for (Lanche lanche : promocao.getLanches()) {
      lanchesPromocao.add(lancheService.buscaPorId(lanche.getIdLanche()));
    }

    promocao.setLanches(lanchesPromocao);

    if (promocao.getPrecoPromocao() == null) {
      for (Lanche lanche : promocao.getLanches()) {
        precoPromocao = precoPromocao.add(lanche.getPrecoLanche());
      }

      if (promocao.getDescontoPromocao() != null) {
        BigDecimal descontoDecimal = precoPromocao.multiply(BigDecimal.valueOf(promocao.getDescontoPromocao()))
            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        precoPromocao = precoPromocao.subtract(descontoDecimal);
      }
    } else {
      precoPromocao = promocao.getPrecoPromocao();

      if (promocao.getDescontoPromocao() != null) {
        BigDecimal descontoDecimal = precoPromocao.multiply(BigDecimal.valueOf(promocao.getDescontoPromocao()))
            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        precoPromocao = precoPromocao.subtract(descontoDecimal);
      }
    }

    promocao.setPrecoPromocao(precoPromocao.setScale(2, RoundingMode.HALF_UP));

    return promocaoRepository.save(promocao);
  }

  public Promocao atualizaPromocao(Long id, Promocao promocao) {
    BigDecimal precoPromocao = BigDecimal.ZERO;
    List<Lanche> lanchesPromocao = new ArrayList<>();

    Promocao promocaoEncontrada = buscaPorId(id);

    if (!promocaoEncontrada.getNomePromocao().equals(promocao.getNomePromocao()) &&
        promocaoRepository.existsByNomePromocao(promocao.getNomePromocao())) {
      throw new PromocaoException("Já existe uma promoção com o nome: " + promocao.getNomePromocao());
    }

    for (Lanche lanche : promocao.getLanches()) {
      lanchesPromocao.add(lancheService.buscaPorId(lanche.getIdLanche()));
    }

    promocao.setLanches(lanchesPromocao);

    if (promocao.getPrecoPromocao() == null) {
      for (Lanche lanche : promocao.getLanches()) {
        precoPromocao = precoPromocao.add(lanche.getPrecoLanche());
      }

      if (promocao.getDescontoPromocao() != null) {
        BigDecimal descontoDecimal = precoPromocao.multiply(BigDecimal.valueOf(promocao.getDescontoPromocao()))
            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        precoPromocao = precoPromocao.subtract(descontoDecimal);
      }
    } else {
      precoPromocao = promocao.getPrecoPromocao();

      if (promocao.getDescontoPromocao() != null) {
        BigDecimal descontoDecimal = precoPromocao.multiply(BigDecimal.valueOf(promocao.getDescontoPromocao()))
            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        precoPromocao = precoPromocao.subtract(descontoDecimal);
      }
    }

    promocao.setPrecoPromocao(precoPromocao.setScale(2, RoundingMode.HALF_UP));

    promocaoEncontrada.setNomePromocao(promocao.getNomePromocao());
    promocaoEncontrada.setDescricaoPromocao(promocao.getDescricaoPromocao());
    promocaoEncontrada.setPrecoPromocao(promocao.getPrecoPromocao());
    promocaoEncontrada.setDescontoPromocao(promocao.getDescontoPromocao());
    promocaoEncontrada.setLanches(promocao.getLanches());

    return promocaoRepository.save(promocaoEncontrada);
  }

  public void deletaPromocao(Long id) {
    buscaPorId(id);
    promocaoRepository.deleteById(id);
  }

  static class PromocaoException extends RuntimeException {
    public PromocaoException(String message) {
      super(message);
    }
  }
}
