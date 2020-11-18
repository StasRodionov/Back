package com.trade_accounting.controllers;


import com.trade_accounting.models.TypeOfPrice;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeOfPriceRestController {

    @GetMapping
    public Response<TypeOfPrice> getTypeOfPrice(@PathVariable(name = "id") Long id) { }

    @GetMapping
    public Response<List<TypeOfPrice>> getTypesOfPrice() {}

    // TODO Refactor to DTO
    @PostMapping
    public Response<?> addTypeOfPrice(@RequestBody TypeOfPrice typeOfPrice) {}

    // TODO Refactor to DTO
    @PutMapping
    public Response<?> updateTypeOfPrice(@RequestBody TypeOfPrice typeOfPrice) {}

    @DeleteMapping
    public Response<?> deleteTypeOfPrice(@PathVariable(name = "id") Long id) {}
}
