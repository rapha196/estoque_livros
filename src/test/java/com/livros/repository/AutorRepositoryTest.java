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

@DataJpaTest
public class AutorRepositoryTest {

	@Autowired
	private AutorRepository autorRepository;

	private Autor autor;

	@BeforeEach
	public void setUp() {

		autor = new Autor();
		autor.setNome("Autor Teste");
	}

	@Test
	@Rollback(false)
	public void testSaveAndFindAutor() {

		Autor savedAutor = autorRepository.save(autor);

		Optional<Autor> foundAutor = autorRepository.findById(savedAutor.getId());

		assertTrue(foundAutor.isPresent());
		assertEquals("Autor Teste", foundAutor.get().getNome());
	}

	@Test
	@Rollback(false)
	public void testDeleteAutor() {
		Autor savedAutor = autorRepository.save(autor);
		autorRepository.delete(savedAutor);

		Optional<Autor> foundAutor = autorRepository.findById(savedAutor.getId());
		assertTrue(foundAutor.isEmpty());
	}
}
