package com.livros.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livros.model.Autor;
import com.livros.repository.AutorRepository;
import com.livros.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository autorRepository;

	@Override
	public Autor save(Autor autor) {
		return autorRepository.save(autor);
	}

	@Override
	public List<Autor> findAll() {
		return autorRepository.findAll();
	}

	@Override
	public Optional<Autor> findById(Long id) {
		return autorRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		autorRepository.deleteById(id);
	}
}
