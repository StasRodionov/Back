package com.trade_accounting.controllers.rest.invoice;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.invoice.InvoiceRestController;
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
@Sql(value = "/Invoice-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@RequiredArgsConstructor
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class InvoiceRestControllerTest {

    @Autowired
    private InvoiceRestController invoiceRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(invoiceRestController, "Invoice Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String invoiceDtoJson = new Gson().toJson(InvoiceDto.builder()
                .id(1L)
                .comment("comment 1")
                .date("2222-11-01T00:01:00")
                .isSpend(false)
                .typeOfInvoice("EXPENSE")
                .companyId(1L)

                .contractorId(1L)
                .warehouseId(1L)
                .build());

        mockMvc.perform(get("/api/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String invoiceDtoJson = new Gson().toJson(InvoiceDto.builder()
                .id(2L)
                .comment("comment 2")
                .date("2222-11-01T00:01:00")
                .typeOfInvoice("RECEIPT")
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .isSpend(false)
                .build());
        mockMvc.perform(post("/api/invoice")
                        .contentType(MediaType.APPLICATION_JSON).content(invoiceDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void update() throws Exception {
        String invoiceDtoJson = new Gson().toJson(InvoiceDto.builder()
                .id(3L)
                .isSpend(false)
                .comment("comment 3")
                .date("2222-11-01T00:03:00")
                .typeOfInvoice("RECEIPT")
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .build());

        mockMvc.perform(put("/api/invoice")
                        .contentType(MediaType.APPLICATION_JSON).content(invoiceDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/invoice"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/invoice/2"))
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

    @Test
    void createAll() throws Exception {
        PurchaseControlDto purchaseControlDto_1 = PurchaseControlDto.builder()
                .id(4L)
                .productNameId(0L)
                .productCode(111L)
                .date("2022-06-01T00:03:00")
                .articleNumber(22L)
                .productMeasure("kg")
                .companyId(1L)
                .warehouseId(0L)
                .contractorId(0L)
                .historyOfSalesId(0L)
                .currentBalanceId(0L)
                .forecastId(0L)
                .build();

        PurchaseControlDto purchaseControlDto_2 = PurchaseControlDto.builder()
                .id(5L)
                .productNameId(1L)
                .productCode(222L)
                .date("2022-06-01T00:04:00")
                .articleNumber(33L)
                .productMeasure("t")
                .companyId(2L)
                .warehouseId(0L)
                .contractorId(0L)
                .historyOfSalesId(0L)
                .currentBalanceId(0L)
                .forecastId(0L)
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
                .andExpect(content().json(purchaseCreateOrderDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
}
