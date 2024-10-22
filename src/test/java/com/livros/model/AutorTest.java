package com.livros.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutorTest {

	private Autor autor;

	@BeforeEach
	public void setUp() {

		autor = new Autor();
	}

	@Test
	public void testAutorGettersAndSetters() {

		autor.setId(1L);
		autor.setNome("Autor Teste");
		autor.setLivros(Collections.emptyList());

		assertEquals(1L, autor.getId());
		assertEquals("Autor Teste", autor.getNome());
		assertEquals(Collections.emptyList(), autor.getLivros());
	}

	@Test
	public void testAutorConstructor() {

		Autor autorComDados = new Autor(2L, "Outro Autor", Collections.emptyList());

		assertEquals(2L, autorComDados.getId());
		assertEquals("Outro Autor", autorComDados.getNome());
		assertEquals(Collections.emptyList(), autorComDados.getLivros());
	}
}
