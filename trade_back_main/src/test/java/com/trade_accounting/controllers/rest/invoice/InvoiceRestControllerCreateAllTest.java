package com.trade_accounting.controllers.rest.invoice;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.dto.invoice.TypeOfOrder;
import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.dto.purchases.PurchaseCreateOrderDto;
import lombok.RequiredArgsConstructor;
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
@Sql(value = "/src/test/resources/Invoice-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/src/test/resources/Product-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@RequiredArgsConstructor
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class InvoiceRestControllerCreateAllTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAllGeneralType() throws Exception {
        PurchaseControlDto purchaseControlDto_1 = PurchaseControlDto.builder()
                .id(1L)
                .productNameId(1L)
                .productCode(1L)
                .date("2022-06-01T00:03:00")
                .articleNumber(22L)
                .productMeasure("kg")
                .companyId(1L)
                .warehouseId(1L)
                .contractorId(1L)
                .historyOfSalesId(1L)
                .currentBalanceId(1L)
                .forecastId(1L)
                .build();

        PurchaseControlDto purchaseControlDto_2 = PurchaseControlDto.builder()
                .id(2L)
                .productNameId(2L)
                .productCode(2L)
                .date("2022-06-01T00:04:00")
                .articleNumber(33L)
                .productMeasure("t")
                .companyId(2L)
                .warehouseId(2L)
                .contractorId(2L)
                .historyOfSalesId(2L)
                .currentBalanceId(2L)
                .forecastId(2L)
                .build();

        List<PurchaseControlDto> purchaseControlDtoList = new ArrayList<>();
        purchaseControlDtoList.add(purchaseControlDto_1);
        purchaseControlDtoList.add(purchaseControlDto_2);

        String purchaseCreateOrderDtoJson = new Gson().toJson(PurchaseCreateOrderDto.builder()
                .purchaseControlDtoList(purchaseControlDtoList)
                .typeOfOrder(TypeOfOrder.GENERAL)
                .build());

        mockMvc.perform(post("/api/invoice/createAll")
                        .contentType(MediaType.APPLICATION_JSON).content(purchaseCreateOrderDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void createAllGroupingType() throws Exception {
        PurchaseControlDto purchaseControlDto_1 = PurchaseControlDto.builder()
                .id(1L)
                .productNameId(1L)
                .productCode(1L)
                .date("2022-06-01T00:03:00")
                .articleNumber(22L)
                .productMeasure("kg")
                .companyId(1L)
                .warehouseId(1L)
                .contractorId(1L)
                .historyOfSalesId(1L)
                .currentBalanceId(1L)
                .forecastId(1L)
                .build();

        PurchaseControlDto purchaseControlDto_2 = PurchaseControlDto.builder()
                .id(2L)
                .productNameId(2L)
                .productCode(2L)
                .date("2022-06-01T00:04:00")
                .articleNumber(33L)
                .productMeasure("t")
                .companyId(2L)
                .warehouseId(2L)
                .contractorId(2L)
                .historyOfSalesId(2L)
                .currentBalanceId(2L)
                .forecastId(2L)
                .build();

        List<PurchaseControlDto> purchaseControlDtoList = new ArrayList<>();
        purchaseControlDtoList.add(purchaseControlDto_1);
        purchaseControlDtoList.add(purchaseControlDto_2);

        String purchaseCreateOrderDtoJson = new Gson().toJson(PurchaseCreateOrderDto.builder()
                .purchaseControlDtoList(purchaseControlDtoList)
                .typeOfOrder(TypeOfOrder.GROUPING_BY_CONTRACTOR)
                .build());

        mockMvc.perform(post("/api/invoice/createAll")
                        .contentType(MediaType.APPLICATION_JSON).content(purchaseCreateOrderDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
