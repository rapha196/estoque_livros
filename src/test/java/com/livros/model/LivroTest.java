package com.livros.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LivroTest {

	private Livro livro;

	@BeforeEach
	public void setUp() {

		livro = new Livro();
	}

	@Test
	public void testLivroGettersAndSetters() {

		Autor autor = new Autor(1L, "Autor Teste", null);
		livro.setId(1L);
		livro.setTitulo("Título Teste");
		livro.setIsbn("ISBN123");
		livro.setAutor(autor);

		assertEquals(1L, livro.getId());
		assertEquals("Título Teste", livro.getTitulo());
		assertEquals("ISBN123", livro.getIsbn());
		assertEquals(autor, livro.getAutor());
	}

	@Test
	public void testLivroConstructor() {

		Autor autor = new Autor(2L, "Outro Autor", null);
		Livro livroComDados = new Livro(2L, "Outro Título", "ISBN456", autor);

		assertEquals(2L, livroComDados.getId());
		assertEquals("Outro Título", livroComDados.getTitulo());
		assertEquals("ISBN456", livroComDados.getIsbn());
		assertEquals(autor, livroComDados.getAutor());
	}
}
