package com.livros.service.impl;

import com.livros.model.Autor;
import com.livros.repository.AutorRepository;
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

public class AutorServiceImplTest {

	@InjectMocks
	private AutorServiceImpl autorService;

	@Mock
	private AutorRepository autorRepository;

	private Autor autor;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		autor = new Autor(1L, "Autor Teste", Collections.emptyList());
	}

	@Test
    public void testSave() {
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        Autor savedAutor = autorService.save(autor);

        assertEquals("Autor Teste", savedAutor.getNome());
        verify(autorRepository, times(1)).save(autor);
    }

	@Test
    public void testFindAll() {
        when(autorRepository.findAll()).thenReturn(Collections.singletonList(autor));

        List<Autor> autores = autorService.findAll();

        assertEquals(1, autores.size());
        assertEquals("Autor Teste", autores.get(0).getNome());
        verify(autorRepository, times(1)).findAll();
    }

	@Test
    public void testFindById() {
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));

        Optional<Autor> foundAutor = autorService.findById(1L);

        assertTrue(foundAutor.isPresent());
        assertEquals("Autor Teste", foundAutor.get().getNome());
        verify(autorRepository, times(1)).findById(1L);
    }

	@Test
	public void testDeleteById() {
		autorService.deleteById(1L);

		verify(autorRepository, times(1)).deleteById(1L);
	}
}
