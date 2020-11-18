package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Company;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyRestController {

    @GetMapping
    public Response<Company> getCompany(@PathVariable(name = "id") Long id) {}

    @GetMapping
    public Response<List<Company>> getCompanies() {}

    //TODO Company - refactor to DTO
    @PostMapping
    public Response<?> addCompany(@RequestBody Company company) {}

    //TODO Company - refactor to DTO
    @PutMapping
    public Response<?> updateCompany(@RequestBody Company company) {}

    @DeleteMapping
    public Response<?> deleteCompany(@PathVariable(name = "id") Long id) {}

}
