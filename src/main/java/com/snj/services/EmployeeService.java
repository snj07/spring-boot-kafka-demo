package com.snj.services;

import com.snj.entities.Employee;
import com.snj.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("EmployeeService")
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository = null;

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createCustomer(Employee emp) {
        return employeeRepository.save(emp);
    }

}
