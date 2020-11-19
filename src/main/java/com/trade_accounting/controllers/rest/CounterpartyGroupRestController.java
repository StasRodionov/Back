package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.CounterpartyGroup;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/counterpartygroup")
public class CounterpartyGroupRestController {

    //Get all counterpartyGroups
    @GetMapping
    public Response<List<CounterpartyGroup>> getCounterpartyGroups() {

    }

    //Create new counterpartyGroup
    @PostMapping
    public Response<?> addCounterpartyGroup(@RequestBody CounterpartyGroup counterpartyGroup){
    }

    //Get counterpartyGroup by id
    @GetMapping
    public Response<CounterpartyGroup> getCounterpartyGroup(@PathVariable(name = "id") Long id) {
    }

    //Update counterpartyGroup by id
    @PutMapping
    public Response<?> updateCounterpartyGroup(@RequestBody CounterpartyGroup counterpartyGroup) {
    }

    //Delete counterpartyGroup by id
    @DeleteMapping
    public Response<?> deleteCounterpartyGroup(@PathVariable(name = "id") Long id) {
    }

}
