package com.livros.service;

import java.util.List;
import java.util.Optional;

import com.livros.model.Livro;

public interface LivroService {

	Livro save(Livro livro);

	List<Livro> findAll();

	Optional<Livro> findById(Long id);

	void deleteById(Long id);
}
