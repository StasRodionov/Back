package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Andrey Melnikov
 * @since 10.08.2021
 */

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "karimogon@mail.ru")
@Sql(value = "/Image-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@RequiredArgsConstructor
public class ImageRestControllerTest {

    @Autowired
    private ImageRestController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExisting() {
        Assertions.assertNotNull(controller, "Error - ImageRestController is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    void getById() throws Exception {
        ImageDto imageDto = ImageDto.builder()
                .id(1L)
                .fileExtension("image_url1")
                .sortNumber("sort_number1")
                .build();

        String dtoJson = new Gson().toJson(imageDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/image/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson));
    }

    @Test
    void create() throws Exception {
        ImageDto imageDto = ImageDto.builder()
                .fileExtension("image_url1")
                .sortNumber("sort_number1")
                .build();

        String dtoJson = new Gson().toJson(imageDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/image")
                        .contentType(MediaType.APPLICATION_JSON).content(dtoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));
    }

    @Test
    void update() throws Exception {
        ImageDto imageDto = ImageDto.builder()
                .fileExtension("UPDATED1")
                .sortNumber("UPDATED1")
                .build();

        String dtoJson = new Gson().toJson(imageDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/image")
                        .contentType(MediaType.APPLICATION_JSON).content(dtoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/image/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

}
