package com.trade_accounting.controllers.rest.units;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.units.CountryDto;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/country-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class CountryRestControllerTest {
    @Autowired
    private CountryRestController countryRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(countryRestController, "CountryRestConroller is null");
    }

    @Test
    void testGetAll() throws Exception {
        this.mockMvc.perform(get("/api/country"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        CountryDto countryDto = CountryDto.builder()
                .id(4L)
                .type("Системный")
                .shortName("Уругвай")
                .fullName("Восточная Республика Уругвай")
                .digitCode("858")
                .twoLetterCode("UY")
                .threeLetterCode("URY")
                .build();

        String countryDtoJson = new Gson().toJson(countryDto);

        this.mockMvc.perform(post("/api/country").contentType(MediaType.APPLICATION_JSON).content(countryDtoJson))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().json(countryDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/country"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        CountryDto countryDtoUpdate = CountryDto.builder()
                .id(4L)
                .type("Системный")
                .shortName("Мали")
                .fullName("Республика Мали")
                .digitCode("466")
                .twoLetterCode("ML")
                .threeLetterCode("MLI")
                .build();

        String countryDtoJson = new Gson().toJson(countryDtoUpdate);

        this.mockMvc.perform(put("/api/country").contentType(MediaType.APPLICATION_JSON).content(countryDtoJson))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().json(countryDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/country"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {

        this.mockMvc.perform(delete("/api/country/3"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/country"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(2)));
    }


}
