package org.example.springbootjwt.controller.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.example.springbootjwt.controller.IRestEmployeeController;
import org.example.springbootjwt.dto.dtoEmployee;
import org.example.springbootjwt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class RestEmployeeControllerImpl implements IRestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    @Override
    public dtoEmployee findEmployeeById(@PathVariable(value = "id") Long id) {
        return null;
    }
}
