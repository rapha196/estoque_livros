package com.livros.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livros.model.Livro;
import com.livros.repository.LivroRepository;
import com.livros.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Override
	public Livro save(Livro livro) {
		return livroRepository.save(livro);
	}

	@Override
	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	@Override
	public Optional<Livro> findById(Long id) {
		return livroRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		livroRepository.deleteById(id);
	}
}
