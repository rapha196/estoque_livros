package com.livros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livros.model.Autor;
import com.livros.model.Livro;
import com.livros.service.AutorService;
import com.livros.service.LivroService;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@Autowired
	private AutorService autorService;

	@PostMapping
	public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {

		Autor autorExistente = autorService.findById(livro.getAutor().getId())
				.orElseThrow(() -> new RuntimeException("Autor não encontrado"));

		livro.setAutor(autorExistente);

		return ResponseEntity.ok(livroService.save(livro));
	}

	@GetMapping
	public List<Livro> getAllLivros() {
		return livroService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
		return livroService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody Livro livro) {
		livro.setId(id);
		return ResponseEntity.ok(livroService.save(livro));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLivro(@PathVariable Long id) {
		livroService.deleteById(id);
		return ResponseEntity.ok("Livro excluído com sucesso.");
	}
}
