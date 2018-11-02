package com.snj.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType   .AUTO)
    @ApiModelProperty(notes = "The database generated id for employee")
    private long id;
    @ApiModelProperty(notes = "The first name of employee")
    private String firstName;
    @ApiModelProperty(notes = "The first name of employee")
    private String lastName;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Employee[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
