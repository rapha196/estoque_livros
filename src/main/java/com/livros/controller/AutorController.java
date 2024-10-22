package com.livros.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/api/autores")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private LivroService livroService;

	@PostMapping
	public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
		return ResponseEntity.ok(autorService.save(autor));
	}

	@GetMapping
	public List<Autor> getAllAutores() {
		return autorService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
		return autorService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Autor> updateAutor(@PathVariable Long id, @RequestBody Autor autor) {
		autor.setId(id);
		return ResponseEntity.ok(autorService.save(autor));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAutor(@PathVariable Long id) {
		autorService.deleteById(id);
		return ResponseEntity.ok("Autor excluído com sucesso.");
	}

	@GetMapping("/test")
	public ResponseEntity<String> testRole() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.ok("Role(s): " + auth.getAuthorities());
	}
}
