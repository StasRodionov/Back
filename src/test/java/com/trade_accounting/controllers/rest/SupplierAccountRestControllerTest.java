package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.ReturnToSupplierDto;
import com.trade_accounting.models.dto.SupplierAccountDto;
import org.junit.Test;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Sql(value = "/supplierAccounts-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
public class SupplierAccountRestControllerTest {

    @Autowired
    SupplierAccountRestController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testRestController() {
        assertNotNull(controller, "Controller is null");
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/api/supplierAccount"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetById() throws Exception {
        String supplierJson = new Gson().toJson(SupplierAccountDto.builder()
                .id(1L)
                .comment("Комментарий 1")
                .contractId(1L)
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2021-07-23 15:10")
                .isSpend(false)
                .build());
        mockMvc.perform(get("/api/supplierAccount/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(supplierJson));
    }

}
