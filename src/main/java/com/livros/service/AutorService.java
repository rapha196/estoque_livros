package com.livros.service;

import java.util.List;
import java.util.Optional;

import com.livros.model.Autor;

public interface AutorService {

	Autor save(Autor autor);

	List<Autor> findAll();

	Optional<Autor> findById(Long id);

	void deleteById(Long id);
}
