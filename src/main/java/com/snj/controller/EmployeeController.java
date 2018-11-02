package com.snj.controller;


import com.snj.entities.Employee;
import com.snj.services.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@Api(value = "employee management", description = "employee management system")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Add a employee data")
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createCustomer(employee);
    }

    @ApiOperation(value = "View a list of available products", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Resource access is forbidden"),
            @ApiResponse(code = 404, message = "The resource is not found")
    }
    )
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
