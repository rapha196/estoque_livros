package com.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.livros.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
