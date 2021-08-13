package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.models.dto.PurchaseControlDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/PurchaseControl-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@RequiredArgsConstructor
class PurchaseControlRestControllerTest {

    @Autowired
    private PurchaseControlRestController purchaseControlRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(purchaseControlRestController, "Purchase Control Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/purchasecontrol"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getById() throws Exception {
        String purchaseControlDtoJson = new Gson().toJson(PurchaseControlDto.builder()
                .id(1L)
                .productName("test1")
                .productCode(1111111L)
                .articleNumber(111L)
                .productMeasure("test1")
                .productQuantity(1111L)
                .build());

        mockMvc.perform(get("/api/purchasecontrol/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(purchaseControlDtoJson));
    }

    @Test
    void create() throws Exception {
        String purchaseControlDtoJson = new Gson().toJson(PurchaseControlDto.builder()
                .productName("test1")
                .productCode(1111111L)
                .articleNumber(111L)
                .productMeasure("test1")
                .productQuantity(1111L)
                .build()
        );

        mockMvc.perform(post("/api/purchasecontrol")
                .contentType(MediaType.APPLICATION_JSON).content(purchaseControlDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(purchaseControlDtoJson));

        mockMvc.perform(get("/api/purchasecontrol"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void update() throws Exception {
        String purchaseControlDtoJson = new Gson().toJson(PurchaseControlDto.builder()
                .id(1L)
                .productName("test1")
                .productCode(1232351L)
                .articleNumber(121L)
                .productMeasure("test1")
                .productQuantity(1561L)
                .build()
        );

        mockMvc.perform(put("/api/purchasecontrol")
                .contentType(MediaType.APPLICATION_JSON).content(purchaseControlDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(purchaseControlDtoJson));
        mockMvc.perform(get("/api/purchasecontrol"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/purchasecontrol/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        mockMvc.perform(get("/api/purchasecontrol"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}