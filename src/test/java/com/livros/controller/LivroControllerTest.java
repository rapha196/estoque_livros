package com.livros.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.livros.model.Autor;
import com.livros.model.Livro;
import com.livros.service.AutorService;
import com.livros.service.LivroService;

/*@ExtendWith(MockitoExtension.class)
@WebMvcTest(LivroController.class)*/
@SpringBootTest
@AutoConfigureMockMvc
public class LivroControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LivroService livroService;

	@MockBean
	private AutorService autorService;

	private Livro livro;
	private Autor autor;

	@BeforeEach
	public void setUp() {
		autor = new Autor(1L, "Autor Teste", Collections.emptyList());
		livro = new Livro(1L, "Título Teste", "ISBN123", autor);
	}

	@Test
	@WithMockUser
	public void testCreateLivro() throws Exception {
	    when(autorService.findById(anyLong())).thenReturn(Optional.of(autor));
	    when(livroService.save(any(Livro.class))).thenReturn(livro);

	    mockMvc.perform(post("/api/livros")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content("{\"titulo\": \"Título Teste\", \"isbn\": \"ISBN123\", \"autor\": {\"id\": 1}}"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.titulo").value("Título Teste"))
	            .andExpect(jsonPath("$.isbn").value("ISBN123"));
	}

	@Test
	@WithMockUser(roles = "USER")
    public void testGetAllLivros() throws Exception {
        when(livroService.findAll()).thenReturn(Collections.singletonList(livro));

        mockMvc.perform(get("/api/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Título Teste"))
                .andExpect(jsonPath("$[0].isbn").value("ISBN123"));
    }

	@Test
	@WithMockUser
	public void testGetLivroById() throws Exception {
	    when(livroService.findById(anyLong())).thenReturn(Optional.of(livro));

	    mockMvc.perform(get("/api/livros/1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.titulo").value("Título Teste"))
	            .andExpect(jsonPath("$.isbn").value("ISBN123"));
	}

	@Test
	@WithMockUser
	public void testUpdateLivro() throws Exception {

		Livro livroExistente = new Livro(1L, "Título Teste", "ISBN123", autor);
		when(livroService.findById(1L)).thenReturn(Optional.of(livroExistente));

		Livro livroAtualizado = new Livro(1L, "Título Atualizado", "ISBN1234", autor);
		when(livroService.save(any(Livro.class))).thenReturn(livroAtualizado);

		mockMvc.perform(put("/api/livros/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"titulo\": \"Título Atualizado\", \"isbn\": \"ISBN1234\", \"autor\": {\"id\": 1}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Título Atualizado"));
	}

    @Test
    @WithMockUser
    public void testDeleteLivro() throws Exception {
        mockMvc.perform(delete("/api/livros/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Livro excluído com sucesso."));
    }
}
