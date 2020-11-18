package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Department;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentRestController {

    //Get all departments
    @GetMapping
    public Response<List<Department>> getDepartments() {}

    //Create new department
    @PostMapping
    public Response<?> addDepartment(@RequestBody Department department){}

    //Get department by id
    @GetMapping
    public Response<Department> getDepartment(@PathVariable(name = "id") Long id) {}

    //Update department
    @PutMapping
    public Response<?> updateDepartment(@RequestBody Department department) {}

    //Delete department by id
    @DeleteMapping
    public Response<?> deleteDepartment(@PathVariable(name = "id") Long id) {}

}
