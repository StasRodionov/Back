package com.trade_accounting.controllers.rest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureMockMvc
@Sql(value = "/agentReports-before.sql")
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
class AgentReportsRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AgentReportsRestController restController;

    @Test
    void controllerIsNotNullTest() {
        assertNotNull(restController, "rest controller is null");
    }

}
