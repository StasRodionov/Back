package com.trade_accounting.controllers.rest;


import com.google.gson.Gson;
import com.trade_accounting.services.impl.TechnicalCardServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/TechnicalCard-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithMockUser("karimogon@mail.ru")
public class TechnicalCardRestControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private TechnicalCardRestController controller;

    @Autowired
    private TechnicalCardServiceImpl technicalCardService;

    @Test
    public void testExistence() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetById() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(1L));

        mockMvc.perform(get("/api/technical_card/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));
    }
//
    @Test
    public void testCreate() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(2L));

        mockMvc.perform(post("/api/technical_card").contentType(MediaType.APPLICATION_JSON)
                .content(technicalCardDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testUpdate() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(3L));

        mockMvc.perform(put("/api/technical_card").contentType(MediaType.APPLICATION_JSON).content(technicalCardDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/technical_card/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
