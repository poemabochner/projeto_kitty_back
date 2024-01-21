package com.kitty.projeto_kitty_back.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kitty.projeto_kitty_back.domain.model.Ingrediente;
import com.kitty.projeto_kitty_back.domain.model.Lanche;
import com.kitty.projeto_kitty_back.domain.repository.LancheRepository;
import com.kitty.projeto_kitty_back.domain.service.IngredienteService;
import com.kitty.projeto_kitty_back.domain.service.LancheService;

class LancheServiceTest {

    @InjectMocks
    private LancheService lancheService;

    @Mock
    private IngredienteService ingredienteService;

    @Mock
    private LancheRepository lancheRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testaCalculoDoPrecoDoLanche() {
        Ingrediente ingrediente1 = new Ingrediente(1L, "Ingrediente1", new BigDecimal("2.50"));
        Ingrediente ingrediente2 = new Ingrediente(2L, "Ingrediente2", new BigDecimal("3.00"));
        List<Ingrediente> ingredientes = List.of(ingrediente1, ingrediente2);

        Lanche lanche = new Lanche();
        lanche.setIngredientes(ingredientes);

        when(ingredienteService.buscaPorId(1L)).thenReturn(ingrediente1);
        when(ingredienteService.buscaPorId(2L)).thenReturn(ingrediente2);
        when(lancheRepository.save(any(Lanche.class))).thenReturn(lanche);

        Lanche resultado = lancheService.cadastraLanche(lanche);

        assertNotNull(resultado);
        assertEquals(new BigDecimal("5.50"), resultado.getPrecoLanche());
        verify(lancheRepository, times(1)).save(any(Lanche.class));
    }
}
