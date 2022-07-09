package com.trade_accounting.controllers.rest.units;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.units.SalesChannelDto;
import com.trade_accounting.services.interfaces.units.SalesChannelService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "karimogon@mail.ru")
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = {"/src/test/resources/SalesChannel-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class SalesChannelRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SalesChannelRestController salesChannelRestController;
    @Autowired
    SalesChannelService salesChannelService;

    @Test
    public void tesExistence() {
        assertThat(salesChannelRestController).isNotNull();
    }

    @Test
    void testGetAll() throws Exception {
        this.mockMvc.perform(get("/api/sales-channel"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));;
    }

    @Test
    void testGetById() throws Exception {
        SalesChannelDto salesChannelDtoFound = SalesChannelDto.builder()
                .id(1L)
                .name("Канал1")
                .type("Тип1")
                .description("Описание1")
                .build();

        String salesChannelDtoJson = new Gson().toJson(salesChannelDtoFound);

        this.mockMvc.perform(get("/api/sales-channel/1"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().json(salesChannelDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        SalesChannelDto salesChannelDtoCreate = SalesChannelDto.builder()
                .id(4L)
                .name("Канал4")
                .type("Тип4")
                .description("Описание4")
                .build();

        String salesChannelDtoJson = new Gson().toJson(salesChannelDtoCreate);

        this.mockMvc.perform(post("/api/sales-channel").contentType(MediaType.APPLICATION_JSON).content(salesChannelDtoJson))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().json(salesChannelDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        this.mockMvc.perform(get("/api/sales-channel"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        SalesChannelDto salesChannelDtoUpdate = SalesChannelDto.builder()
                .id(1L)
                .name("КаналNew")
                .type("Тип1")
                .description("Описание1")
                .build();

        String salesChannelDtoJson = new Gson().toJson(salesChannelDtoUpdate);

        this.mockMvc.perform(put("/api/sales-channel").contentType(MediaType.APPLICATION_JSON).content(salesChannelDtoJson))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().json(salesChannelDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        this.mockMvc.perform(get("/api/sales-channel"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception{

        this.mockMvc.perform(delete("/api/sales-channel/1"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        this.mockMvc.perform(get("/api/sales-channel"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}