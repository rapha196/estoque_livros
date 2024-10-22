package com.livros.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.livros.model.Autor;
import com.livros.service.AutorService;

@SpringBootTest
@AutoConfigureMockMvc
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorService autorService;

    @Test
    @WithMockUser
    public void testCreateAutor() throws Exception {
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setNome("Autor Teste");

        Mockito.when(autorService.save(any(Autor.class))).thenReturn(autor);

        mockMvc.perform(post("/api/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Autor Teste\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Autor Teste"));
    }

    @Test
    @WithMockUser
    public void testGetAllAutores() throws Exception {
        Autor autor1 = new Autor(1L, "Autor 1", new ArrayList<>());
        Autor autor2 = new Autor(2L, "Autor 2", new ArrayList<>());

        Mockito.when(autorService.findAll()).thenReturn(Arrays.asList(autor1, autor2));

        mockMvc.perform(get("/api/autores"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nome").value("Autor 1"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].nome").value("Autor 2"));
    }

    @Test
    @WithMockUser
    public void testGetAutorById() throws Exception {
        Autor autor = new Autor(1L, "Autor Teste", new ArrayList<>());

        Mockito.when(autorService.findById(anyLong())).thenReturn(Optional.of(autor));

        mockMvc.perform(get("/api/autores/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nome").value("Autor Teste"));
    }

    @Test
    @WithMockUser
    public void testGetAutorById_NotFound() throws Exception {
        Mockito.when(autorService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/autores/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testUpdateAutor() throws Exception {

    	Autor autorExistente = new Autor(1L, "Autor Teste", new ArrayList<>());

        Mockito.when(autorService.findById(anyLong())).thenReturn(Optional.of(autorExistente));

        Autor autorAtualizado = new Autor(1L, "Autor Atualizado", new ArrayList<>());

        Mockito.when(autorService.save(any(Autor.class))).thenReturn(autorAtualizado);

        mockMvc.perform(put("/api/autores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Autor Atualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Autor Atualizado"));
    }

    @Test
    @WithMockUser
    public void testDeleteAutor() throws Exception {
        mockMvc.perform(delete("/api/autores/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Autor excluído com sucesso."));
    }
}
