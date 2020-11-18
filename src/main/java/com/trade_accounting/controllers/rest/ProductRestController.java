package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Product;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {


    //Get all Products
    @GetMapping
    public Response<List<Product>> getProducts() {}

    //Create new Product
    @PostMapping
    public Response<?> addProduct(@RequestBody Product product){}

    //Get Product by id
    @GetMapping
    public Response<Product> getProduct(@PathVariable(name = "id") Long id) {}

    //Update Product
    @PutMapping
    public Response<?> updateProduct(@RequestBody Product product) {}

    //Delete Product by id
    @DeleteMapping
    public Response<?> deleteProduct(@PathVariable(name = "id") Long id) {}
}
