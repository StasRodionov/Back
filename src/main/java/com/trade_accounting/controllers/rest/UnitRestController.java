package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Unit;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnitRestController {

    //Get all Units
    @GetMapping
    public Response<List<Unit>> getUnits() {}

    //Create new Unit
    @PostMapping
    public Response<?> addUnit(@RequestBody Unit unit){}

    //Get Unit by id
    @GetMapping
    public Response<Unit> getUnit(@PathVariable(name = "id") Long id) {}

    //Update Unit
    @PutMapping
    public Response<?> updateUnit(@RequestBody Unit unit) {}

    //Delete Unit by id
    @DeleteMapping
    public Response<?> deleteUnit(@PathVariable(name = "id") Long id) {}

}
