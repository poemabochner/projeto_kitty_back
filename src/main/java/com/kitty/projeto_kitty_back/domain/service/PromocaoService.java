package com.kitty.projeto_kitty_back.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public Promocao criaPromocao(Promocao promocao) {
    return promocaoRepository.save(promocao);
  }

  public Promocao atualizaPromocao(Long id, Promocao promocao) {

    Promocao promocaoEncontrada = buscaPorId(id);

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
