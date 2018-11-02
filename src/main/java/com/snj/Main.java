package com.snj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.snj")
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

//        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
//        EmployeeRepository repository = context.getBean(EmployeeRepository.class);
//
//        // save a couple of Employees
//        repository.save(new Employee("A", "B"));
//        repository.save(new Employee("C", "D"));
//        repository.save(new Employee("E", "F"));
//        repository.save(new Employee("G", "H"));
//        repository.save(new Employee("I", "J"));
//
//        // fetch all Employees
//        Iterable<Employee> Employees = repository.findAll();
//        System.out.println("Employees found with findAll():");
//        System.out.println("-------------------------------");
//        for (Employee Employee : Employees) {
//            System.out.println(Employee);
//        }
//        System.out.println();


        // fetch an individual Employee by ID
//        Employee Employee = repository.findByEmpId()yId(1L);
//        System.out.println("Employee found with findOne(1L):");
//        System.out.println("--------------------------------");
//        System.out.println(Employee);
//        System.out.println();

        // fetch Employees by last name
//        List<Employee> empList = repository.findByEmpId(1L);
//        System.out.println("Employee found with id :1 :");
//        System.out.println("--------------------------------------------");
//        for (Employee e : empList) {
//            System.out.println(e);
//        }

    }
}
