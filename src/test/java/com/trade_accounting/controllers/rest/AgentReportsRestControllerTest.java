package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.ContractorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureMockMvc
@Sql(value = "/agentReports-before.sql")
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
class AgentReportsRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AgentReportsRestController restController;

    @Autowired
    CompanyService companyService;

    @Autowired
    ContractorService contractorService;

    @Test
    void controllerIsNotNullTest() {
        assertNotNull(restController, "rest controller is null");
    }

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(get("/api/agentReports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getByIdTest() throws Exception {
        mockMvc.perform(get("/api/agentReports/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$.id", hasToString("1")));
    }

    @Test
    void createTest() throws Exception {
        String jsonDto = new Gson().toJson(AgentReportsDto.builder()
                .id(4L)
                .companyId(1L)
                .contractorId(1L)
                .comitentSum(1L)
                .commentary("Комментарий 1")
                .documentType(".doc")
                .number("1")
                .paid(1L)
                .printed(1L)
                .remunirationSum(1L)
                .sent(1L)
                .status("ok")
                .date(LocalDateTime.now().toString())
                .sum(1L)
                .build());
        mockMvc.perform(post("/api/agentReports").contentType(MediaType.APPLICATION_JSON)
                .content(jsonDto))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/agentReports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void updateTest() throws Exception {
        String jsonDto = new Gson().toJson(AgentReportsDto.builder()
                .id(1L)
                .companyId(1L)
                .contractorId(1L)
                .comitentSum(1L)
                .commentary("Комментарий 1")
                .documentType(".doc")
                .number("1")
                .paid(1L)
                .printed(1L)
                .remunirationSum(1L)
                .sent(1L)
                .status("error")
                .date("2015-10-06T06:37:59")
                .sum(1L)
                .build());
        mockMvc.perform(put("/api/agentReports").contentType(MediaType.APPLICATION_JSON)
                .content(jsonDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$.status", hasToString("error")));
        mockMvc.perform(get("/api/agentReports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/api/agentReports/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/agentReports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
