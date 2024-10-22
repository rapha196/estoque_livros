package com.livros.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

	private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

	@Test
	public void testHandleAllExceptions() {

		Exception exception = new Exception("Erro interno do servidor");
		ResponseEntity<String> responseEntity = globalExceptionHandler.handleAllExceptions(exception);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertEquals("Erro interno do servidor", responseEntity.getBody());
	}
}
