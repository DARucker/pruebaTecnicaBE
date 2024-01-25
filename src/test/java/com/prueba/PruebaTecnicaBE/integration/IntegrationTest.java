package com.prueba.PruebaTecnicaBE.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import com.prueba.PruebaTecnicaBE.repository.SuperheroeRepository;
import com.prueba.PruebaTecnicaBE.service.ISuperHeroeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.Startables;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTest {

    @Container
    static final MySQLContainer container = new MySQLContainer<>("mysql");

    @DynamicPropertySource
    public static void init(DynamicPropertyRegistry registry) {
        Startables.deepStart(container).join();
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ISuperHeroeService superHeroeService;
    @Autowired
    private SuperheroeRepository superheroeRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Save")
    void SaveIntegrationTest() throws Exception {

        Superheroe superman = new Superheroe(1, "Superman");

        ResultActions createResult = mockMvc.perform(post("/api/v1/superheroes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superman)))
                        .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("Find by ID")
    void FindByIdIntegrationTest() throws Exception {

        Superheroe batman = new Superheroe(1, "Batman");
        superheroeRepository.save(batman);

        mockMvc.perform(get("/api/v1/superheroes/findById/{id}", batman.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(batman.getId()))
                .andExpect(jsonPath("$.name").value("Batman"));
    }
    @Test
    @DisplayName("Find All")
    void FindAllIntegrationTest() throws Exception {

        superheroeRepository.deleteAll();
        Superheroe batman = new Superheroe(1, "Batman");
        Superheroe robien = new Superheroe(2, "Robin");
        superheroeRepository.save(batman);
        superheroeRepository.save(robien);

        mockMvc.perform(get("/api/v1/superheroes/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name").value("Batman"))
                .andExpect(jsonPath("$[1].name").value("Robin"));

    }
    @Test
    @DisplayName("Update")
    void UpdateIntegrationTest() throws Exception {

        Superheroe updateSuperheroe = new Superheroe(1, "Superman");
        superheroeRepository.save(updateSuperheroe);
        updateSuperheroe.setName("Robin");

        mockMvc.perform(put("/api/v1/superheroes/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateSuperheroe)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name")
                        .value("Robin"));
    }

    @Test
    @DisplayName("Delete")
    void DeleteIntegrationTest() throws Exception {

        Superheroe deleteSuperheroe = new Superheroe(1, "El Zorro");
        superheroeRepository.save(deleteSuperheroe);

        mockMvc.perform(delete("/api/v1/superheroes/delete/{id}", deleteSuperheroe.getId()))
                .andExpect(status().isOk());

        // Verify expected status afeter deletion
        mockMvc.perform(get("/api/v1/superheroes/findById/{id}", deleteSuperheroe.getId()))
                .andExpect(status().isNotFound());
    }
}