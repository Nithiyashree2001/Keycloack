package com.test.keycloakdemo.service;

import com.test.keycloakdemo.entity.Employee;
import com.test.keycloakdemo.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void initializeEmpTable()
    {
        employeeRepository.saveAll(
                Stream.of(
                        new Employee("nith",8000000L),
                        new Employee("ss",90000000L)

                ).collect(Collectors.toList())
        );
    }

    public Employee getEmpById(Long empId)
    {
        return employeeRepository.findById(empId).orElse(null);
    }

    public List<Employee> getAllEmp()
    {
        return employeeRepository.findAll();
    }
}
