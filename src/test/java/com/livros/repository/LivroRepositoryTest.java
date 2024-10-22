package com.livros.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.livros.model.Autor;
import com.livros.model.Livro;

@DataJpaTest
public class LivroRepositoryTest {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	private Livro livro;
	private Autor autor;

	@BeforeEach
	public void setUp() {

		autor = new Autor();
		autor.setNome("Autor Teste");
		autorRepository.save(autor);

		livro = new Livro();
		livro.setTitulo("Título Teste");
		livro.setIsbn("ISBN123");
		livro.setAutor(autor);
	}

	@Test
	@Rollback(false)
	public void testSaveAndFindLivro() {

		Livro savedLivro = livroRepository.save(livro);

		Optional<Livro> foundLivro = livroRepository.findById(savedLivro.getId());

		assertTrue(foundLivro.isPresent());
		assertEquals("Título Teste", foundLivro.get().getTitulo());
		assertEquals("ISBN123", foundLivro.get().getIsbn());
		assertEquals(autor.getId(), foundLivro.get().getAutor().getId());
	}

	@Test
	@Rollback(false)
	public void testDeleteLivro() {

		Livro savedLivro = livroRepository.save(livro);
		livroRepository.delete(savedLivro);

		Optional<Livro> foundLivro = livroRepository.findById(savedLivro.getId());
		assertTrue(foundLivro.isEmpty());
	}
}
