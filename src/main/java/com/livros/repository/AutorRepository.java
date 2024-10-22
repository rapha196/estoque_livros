package com.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.livros.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
