package com.livros.service.impl;

import com.livros.model.Livro;
import com.livros.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LivroServiceImplTest {

	@InjectMocks
	private LivroServiceImpl livroService;

	@Mock
	private LivroRepository livroRepository;

	private Livro livro;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		livro = new Livro(1L, "Título Teste", "ISBN123", null);
	}

	@Test
    public void testSave() {
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro savedLivro = livroService.save(livro);

        assertEquals("Título Teste", savedLivro.getTitulo());
        verify(livroRepository, times(1)).save(livro);
    }

	@Test
    public void testFindAll() {
        when(livroRepository.findAll()).thenReturn(Collections.singletonList(livro));

        List<Livro> livros = livroService.findAll();

        assertEquals(1, livros.size());
        assertEquals("Título Teste", livros.get(0).getTitulo());
        verify(livroRepository, times(1)).findAll();
    }

	@Test
    public void testFindById() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        Optional<Livro> foundLivro = livroService.findById(1L);

        assertTrue(foundLivro.isPresent());
        assertEquals("Título Teste", foundLivro.get().getTitulo());
        verify(livroRepository, times(1)).findById(1L);
    }

	@Test
	public void testDeleteById() {
		livroService.deleteById(1L);

		verify(livroRepository, times(1)).deleteById(1L);
	}
}
