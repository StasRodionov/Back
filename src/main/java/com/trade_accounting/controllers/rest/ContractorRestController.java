package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Contractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContractorRestController {

    @GetMapping
    public Response<List<Contractor>> getContractors(){
        final List<Contractor> contractors = null;
        return contractors != null &&  !contractors.isEmpty()
                ? new Response<>(contractors, HttpStatus.OK)
                : new Response<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public Response<Contractor> getContractor(){
        final Contractor contractor = null;
        return contractor != null
                ? new Response<>(contractor, HttpStatus.OK)
                : new Response<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Response<?> addContractor(@RequestBody Contractor contractor){
        //addContractor;
        return new Response<>(HttpStatus.CREATED);
    }

    @PutMapping
    public Response<?> updateContractor(@RequestBody Contractor contractor){
        //updateContractor
        return new Response<>(HttpStatus.OK);
    }

    @DeleteMapping
    public Response<?> deleteContractor(@PathVariable(name = "id") Long id) {
        //removeContractor;
        return new Response<>(HttpStatus.OK);
    }
}
