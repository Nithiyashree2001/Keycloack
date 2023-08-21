package com.test.keycloakdemo.controller;

import com.test.keycloakdemo.entity.Employee;
import com.test.keycloakdemo.service.EmployeeService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //user
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id)
    {
        return employeeService.getEmpById(id);
    }

    //admin
    @GetMapping("/all")
    @PreAuthorize("hasRole('client_admin')")
    public List<Employee> getAll()
    {
        return employeeService.getAllEmp();
    }
}
