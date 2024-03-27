package com.epam.testproject.dto;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private Integer id;
    private String firstName;
    private String secondName;
    private Long salary;
    private Integer managerId;


    public Employee() {
    }

    public Employee(Integer id, String firstName, String secondName, Long salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", salary=" + salary +
                ", managerId=" + managerId + "}\n";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

  }
