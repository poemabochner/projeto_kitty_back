package com.kitty.projeto_kitty_back.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kitty.projeto_kitty_back.domain.model.Lanche;
import com.kitty.projeto_kitty_back.domain.model.Promocao;
import com.kitty.projeto_kitty_back.domain.repository.PromocaoRepository;
import com.kitty.projeto_kitty_back.domain.service.LancheService;
import com.kitty.projeto_kitty_back.domain.service.PromocaoService;

class PromocaoServiceTest {

    @InjectMocks
    private PromocaoService promocaoService;

    @Mock
    private LancheService lancheService;

    @Mock
    private PromocaoRepository promocaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testaCadastraPromocaoSemDescontoESemPreco() {
        Lanche lanche1 = new Lanche(1L, "Lanche1", "Descrição1", new BigDecimal("10.00"), new ArrayList<>());
        Lanche lanche2 = new Lanche(2L, "Lanche2", "Descrição2", new BigDecimal("15.00"), new ArrayList<>());
        List<Lanche> lanches = List.of(lanche1, lanche2);

        Promocao promocao = new Promocao(null, "PromoçãoTeste", "DescriçãoPromoção", null, null, lanches);

        when(lancheService.buscaPorId(1L)).thenReturn(lanche1);
        when(lancheService.buscaPorId(2L)).thenReturn(lanche2);
        when(promocaoRepository.save(any())).thenReturn(promocao);

        Promocao result = promocaoService.cadastraPromocao(promocao);

        assertNotNull(result);
        assertEquals(new BigDecimal("25.00"), result.getPrecoPromocao());
        verify(promocaoRepository, times(1)).save(any());
    }

    @Test
    void testaCadastraPromocaoComDescontoESemPreco() {
        Lanche lanche1 = new Lanche(1L, "Lanche1", "Descrição1", new BigDecimal("10.00"), new ArrayList<>());
        Lanche lanche2 = new Lanche(2L, "Lanche2", "Descrição2", new BigDecimal("15.00"), new ArrayList<>());
        List<Lanche> lanches = List.of(lanche1, lanche2);

        Promocao promocao = new Promocao(null, "PromoçãoTeste", "DescriçãoPromoção", null, 10, lanches);

        when(lancheService.buscaPorId(1L)).thenReturn(lanche1);
        when(lancheService.buscaPorId(2L)).thenReturn(lanche2);
        when(promocaoRepository.save(any())).thenReturn(promocao);

        Promocao result = promocaoService.cadastraPromocao(promocao);

        assertNotNull(result);
        assertEquals(new BigDecimal("22.50"), result.getPrecoPromocao());
        verify(promocaoRepository, times(1)).save(any());
    }

    @Test
    void testaCadastraPromocaoSemDescontoEComPreco() {
        Lanche lanche1 = new Lanche(1L, "Lanche1", "Descrição1", new BigDecimal("10.00"), new ArrayList<>());
        Lanche lanche2 = new Lanche(2L, "Lanche2", "Descrição2", new BigDecimal("15.00"), new ArrayList<>());
        List<Lanche> lanches = List.of(lanche1, lanche2);

        Promocao promocao = new Promocao(null, "PromoçãoTeste", "DescriçãoPromoção", new BigDecimal("24.00"), null, lanches);

        when(lancheService.buscaPorId(1L)).thenReturn(lanche1);
        when(lancheService.buscaPorId(2L)).thenReturn(lanche2);
        when(promocaoRepository.save(any())).thenReturn(promocao);

        Promocao result = promocaoService.cadastraPromocao(promocao);

        assertNotNull(result);
        assertEquals(new BigDecimal("24.00"), result.getPrecoPromocao());
        verify(promocaoRepository, times(1)).save(any());
    }

    @Test
    void testaCadastraPromocaoComDescontoEComPreco() {
        Lanche lanche1 = new Lanche(1L, "Lanche1", "Descrição1", new BigDecimal("10.00"), new ArrayList<>());
        Lanche lanche2 = new Lanche(2L, "Lanche2", "Descrição2", new BigDecimal("15.00"), new ArrayList<>());
        List<Lanche> lanches = List.of(lanche1, lanche2);

        Promocao promocao = new Promocao(null, "PromoçãoTeste", "DescriçãoPromoção", new BigDecimal("20.00"), 10, lanches);

        when(lancheService.buscaPorId(1L)).thenReturn(lanche1);
        when(lancheService.buscaPorId(2L)).thenReturn(lanche2);
        when(promocaoRepository.save(any())).thenReturn(promocao);

        Promocao result = promocaoService.cadastraPromocao(promocao);

        assertNotNull(result);
        assertEquals(new BigDecimal("18.00"), result.getPrecoPromocao());
        verify(promocaoRepository, times(1)).save(any());
    }
}
