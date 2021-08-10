package com.trade_accounting.controllers.rest;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Andrey Melnikov
 * @since 10.08.2021
 */

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "karimogon@mail.ru")
@Sql(value = "/Image-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@RequiredArgsConstructor
public class ImageRestControllerTest {

    @Autowired
    private ImageRestController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExisting(){
        Assertions.assertNotNull(controller, "Error - ImageRestController is null");
    }

    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

}
