package com.trade_accounting.controllers.rest.client;


import com.google.gson.Gson;
import com.trade_accounting.models.dto.client.AccountDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.*;
import com.trade_accounting.models.entity.util.Image;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/account-before.sql")
@WithMockUser
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class AccountRestControllerTest {


    @Autowired
    private AccountRestController accountRestController;

    @Autowired
    private MockMvc mockMvc;


    @Test
     void testExistence() throws Exception {
        assertNotNull(accountRestController, "Account Rest controller is null");
    }
    @Test
     void testGetAll() throws Exception {
        mockMvc.perform(get("/api/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetById() throws Exception {
        String accountJson = new Gson().toJson(AccountDto.builder().id(3L).build());
        mockMvc.perform(get("/api/account/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(accountJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {

        AccountDto createdAccount = AccountDto.builder()
                .id(6L)
                .employeeIds(Set.of(6L,7L))
                .build();

        Account account = Account.builder()
                .employee(new Employee())
                .build();

        EmployeeDto createdEmployee = EmployeeDto.builder()
                .lastName("created")
                .firstName("created")
                .middleName("created")
                .sortNumber("created")
                .phone("created")
                .inn("012341234234")
                .description("created")
                .email("hools@252")
                .password("9876")
                .departmentDtoId(5L)
                .imageDtoId(1L)
                .positionDtoId(4L)
                .roleDtoIds(Set.of(2L))
                .build();

        String createdAccountJson = new Gson().toJson(account);
        String createdEmployeeJson = new Gson().toJson(createdEmployee);

        mockMvc.perform(post("/api/account").contentType(MediaType.APPLICATION_JSON)
                        .content(createdEmployeeJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdAccountJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

    }


   @Test
     void testUpdate() throws Exception {

       EmployeeDto createdEmployee = EmployeeDto.builder()
               .lastName("petrov")
               .firstName("ivan")
               .middleName("ivanovich")
               .sortNumber("created")
               .phone("created")
               .inn("012341234234")
               .description("created")
               .email("created")
               .password("created")
               .departmentDtoId(6L)
               .imageDtoId(1L)
               .positionDtoId(4L)
               .roleDtoIds(Set.of(2L))
               .build();

       Employee employee = Employee.builder()
               .lastName("petrov")
               .firstName("ivan")
               .middleName("ivanovich")
               .sortNumber("created")
               .phone("created")
               .inn("012341234234")
               .description("created")
               .email("created")
               .password("created")
               .build();

        String updatedEmployeeJson = new Gson().toJson(createdEmployee);
        String updateEmployee = new Gson().toJson(employee);
        mockMvc.perform(put("/api/account").contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updateEmployee))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

       mockMvc.perform(get("/api/account"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(authenticated())
               .andExpect(jsonPath("$", hasSize(6)));

    }
    @SneakyThrows
    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/account/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }


}
