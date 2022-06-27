package com.trade_accounting.controllers.rest.warehouse;


import com.google.gson.Gson;
import com.trade_accounting.models.dto.warehouse.KitDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/Kit-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/Product-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/ProductPrice-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class KitRestControllerTest {

    @Autowired
    private KitRestController kitRestController;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void testExistence() {
        assertNotNull(kitRestController, "KitRestController is null");
    }

    @SneakyThrows
    @Test
    void testGetAll() {
        mockMvc.perform(get("/api/kit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testGetById() {
        KitDto kitDto = KitDto.builder()
                .id(1L)
                .name("Джентельменский набор №1")
                .description("Костюм тройка, туфли, галстук")
                .archive(false)
                .weight(BigDecimal.valueOf(0.0))
                .volume(BigDecimal.valueOf(0.0))
                .purchasePrice(BigDecimal.valueOf(33.33))
                .unitId(1L)
                .contractorId(1L)
                .taxSystemId(1L)
                .itemNumber(4)
                .saleTax("20")
                .employeeId(1L)
                .departmentId(1L)
                .accessAll(false)
                .build();

        String kitDtoJson = new Gson().toJson(kitDto);

        mockMvc.perform(get("/api/kit/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(kitDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testCreate() {
        List<Long> productIds = new ArrayList<>();
        productIds.add(1L);
        productIds.add(2L);

        List<Long> productPriceIds = new ArrayList<>();
        productPriceIds.add(1L);
        productPriceIds.add(2L);


        KitDto kitDto = KitDto.builder()
                .id(4L)
                .name("Джентельменский набор №4")
                .description("Кожанная куртка, штаны, рубашка с длинным руковом")
                .archive(false)
                .weight(BigDecimal.valueOf(0.0))
                .volume(BigDecimal.valueOf(0.0))
                .purchasePrice(BigDecimal.valueOf(33.33))
                .unitId(1L)
                .contractorId(1L)
                .taxSystemId(1L)
                .itemNumber(4)
                .saleTax("20")
                .employeeId(1L)
                .departmentId(1L)
                .accessAll(false)
                .productIds(productIds)
                .productPriceIds(productPriceIds)
                .productGroupId(1L)
                .build();

        String kitDtoJson = new Gson().toJson(kitDto);

        mockMvc.perform(post("/api/kit").contentType(MediaType.APPLICATION_JSON)
                        .content(kitDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(kitDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/kit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @SneakyThrows
    @Test
    void testUpdate() {
        List<Long> productIds = new ArrayList<>();
        productIds.add(1L);
        productIds.add(2L);

        List<Long> productPriceIds = new ArrayList<>();
        productPriceIds.add(1L);
        productPriceIds.add(2L);


        KitDto kitDto = KitDto.builder()
                .id(1L)
                .name("Джентельменский набор №4")
                .description("Кожанная куртка, штаны, рубашка с длинным руковом")
                .archive(false)
                .weight(BigDecimal.valueOf(0.0))
                .volume(BigDecimal.valueOf(0.0))
                .purchasePrice(BigDecimal.valueOf(33.33))
                .unitId(1L)
                .contractorId(1L)
                .taxSystemId(1L)
                .itemNumber(4)
                .saleTax("20")
                .employeeId(1L)
                .departmentId(1L)
                .accessAll(false)
                .productIds(productIds)
                .productPriceIds(productPriceIds)
                .productGroupId(1L)
                .build();

        String kitDtoJson = new Gson().toJson(kitDto);

        mockMvc.perform(put("/api/kit").contentType(MediaType.APPLICATION_JSON)
                        .content(kitDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(kitDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/kit"))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void testDelete() {
        mockMvc.perform(delete("/api/kit/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/kit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
