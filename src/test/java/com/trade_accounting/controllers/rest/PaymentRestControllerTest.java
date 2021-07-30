package com.trade_accounting.controllers.rest;

import com.google.gson.Gson;
import com.trade_accounting.models.PaymentMethods;
import com.trade_accounting.models.TypeOfPayment;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/Payment-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@RequiredArgsConstructor
class PaymentRestControllerTest {
    @Autowired
    private PaymentRestController paymentRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(paymentRestController, "Payment Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/payment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getById() throws Exception {
        String paymentDtoJson = new Gson().toJson(PaymentDto.builder()
                .id(1L)
                .number("1")
                .paymentMethods(PaymentMethods.CASH)
                .sum(BigDecimal.valueOf(100))
                .time(LocalDateTime.parse("2021-07-30 13:23:24.249491", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")))
                .typeOfPayment(TypeOfPayment.INCOMING)
                .companyDto(CompanyDto.builder()
                        .id(1L)
                        .build())
                .contractDto(ContractDto.builder()
                        .id(1L)
                        .build())
                .contractorDto(ContractorDto.builder()
                        .id(2L)
                        .build())
                .projectDto(ProjectDto.builder()
                        .id(2L)
                        .build()));

        mockMvc.perform(get("/api/payment/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(paymentDtoJson));
    }

    @Test
    void create() throws Exception {
        String internalOrderDtoJson = new Gson().toJson(InternalOrderDto.builder()
                .date("1234-12-12 12:34")
                .isSent(true)
                .isPrint(true)
                .companyId(4L)
                .warehouseId(1L)
                .comment("Комментарий 1")
                .internalOrderProductsIds(List.of(1L, 2L, 3L))
                .build()
        );

        mockMvc.perform(post("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON).content(internalOrderDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(internalOrderDtoJson));

        mockMvc.perform(get("/api/payment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void filterAll() {
    }

    @Test
    void search() {
    }
}