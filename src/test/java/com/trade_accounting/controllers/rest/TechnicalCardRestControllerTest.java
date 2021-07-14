package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.services.interfaces.TechnicalCardGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/TechnicalCard-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
public class TechnicalCardRestControllerTest {

    @Autowired
    private TechnicalCardRestController technicalCardRestController;

    @Autowired
    private TechnicalCardGroupService technicalCardGroupService;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(technicalCardRestController, "TechnicalCard Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testGetById() throws Exception {
        TechnicalCardDto technicalCardDto = TechnicalCardDto.builder()
                .id(1L)
                .comment("Комментарий 1")
                .name("Техническая карта №1")
                .productionCost("1000")
                .technicalCardGroupDto(technicalCardGroupService.getById(1L))
                .build();

        String technicalCardDtoJson = new Gson().toJson(technicalCardDto);

        mockMvc.perform(get("/api/technical_card/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));
    }

    @Test
    void testCreate() throws Exception {
        TechnicalCardDto technicalCardDto = TechnicalCardDto.builder()
                .id(4L)
                .comment("Комментарий 4")
                .name("Техническая карта №4")
                .productionCost("4000")
                .technicalCardGroupDto(technicalCardGroupService.getById(2L))
                .build();

        String technicalCardDtoJson = new Gson().toJson(technicalCardDto);

        mockMvc.perform(post("/api/technical_card").contentType(MediaType.APPLICATION_JSON).content(technicalCardDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        TechnicalCardDto technicalCardDto = TechnicalCardDto.builder()
                .id(3L)
                .comment("Комментарий 1")
                .name("Техническая карта №1")
                .productionCost("1000")
                .technicalCardGroupDto(technicalCardGroupService.getById(1L))
                .build();

        String technicalCardDtoJson = new Gson().toJson(technicalCardDto);

        mockMvc.perform(put("/api/technical_card").contentType(MediaType.APPLICATION_JSON).content(technicalCardDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
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
