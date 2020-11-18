package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Employee;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeRestController {


    //Get all Employees
    @GetMapping
    public Response<List<Employee>> getEmployees() {}

    //Create new Employee
    @PostMapping
    public Response<?> addEmployee(@RequestBody Employee employee){}

    //Get Employee by id
    @GetMapping
    public Response<Employee> getEmployee(@PathVariable(name = "id") Long id) {}

    //Update Employee
    @PutMapping
    public Response<?> updateEmployee(@RequestBody Employee employee) {}

    //Delete Employee by id
    @DeleteMapping
    public Response<?> deleteEmployee(@PathVariable(name = "id") Long id) {}
}
