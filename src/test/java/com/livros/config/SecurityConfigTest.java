package com.livros.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebSecurity
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecurityConfig securityConfig;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testPublicEndpoint() throws Exception {
        mockMvc.perform(get("/api/autores"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testAuthenticatedEndpoint() throws Exception {

    	mockMvc.perform(get("/api/livros"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUserDetailsService() {
        UserDetailsService userDetailsService = securityConfig.userDetailsService(securityConfig.passwordEncoder());

        assertNotNull(userDetailsService);
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        assertNotNull(passwordEncoder);
        assertNotNull(passwordEncoder.encode("testPassword"));
    }
}
