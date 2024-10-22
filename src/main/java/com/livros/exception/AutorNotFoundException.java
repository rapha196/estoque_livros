package com.livros.exception;

public class AutorNotFoundException extends RuntimeException {
	public AutorNotFoundException(String message) {
		super(message);
	}
}
