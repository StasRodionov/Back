package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.PriceListDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

/**
 * @author Andrey Melnikov
 * @since 05.08.2021
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@WithUserDetails(value = "karimogon@mail.ru")
@Sql(value = "/PriceList-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@RequiredArgsConstructor
public class PriceListRestControllerTest {

    @Autowired
    private PriceListRestController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExisting() {
        Assertions.assertNotNull(controller, "Failure - controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/priceList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    void getById(){
        PriceListDto priceListDto = PriceListDto.builder()
                .id(1L)
                .number("number1")
                .time(LocalDateTime.now()) // да твоюж мать ... о5 ДТО не доделан ...(((
                .companyId(1L)
                .sent(0L)
                .printed(1L)
                .commentary("comment1")
                .build();
    }

}
