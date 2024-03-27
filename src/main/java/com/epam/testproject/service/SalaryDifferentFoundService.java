package com.epam.testproject.service;

import com.epam.testproject.dto.Employee;
import com.epam.testproject.util.PropertiesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.ResourceBundle;

public class SalaryDifferentFoundService {

    private List<Employee> employees;
    private static final ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    public SalaryDifferentFoundService(List<Employee> employees) {
        this.employees = employees;
    }

    public List<String> foundManagersWhoEarnLess() {
        List<String> resultSet = new ArrayList<>();
        for (Employee employee : employees) {
            OptionalDouble averageSalary = getAverageSalary(employee);
            try {
                if (averageSalary.isPresent()) {
                    if (employee.getSalary() < averageSalary.getAsDouble() * PropertiesUtil.MIN_RATE) {
                        resultSet.add(String.format(messageBundle.getString("salary_defferent.min_sallary"), employee.getId(), employee.getFirstName(), employee.getSecondName()));
                    }
                    if (employee.getSalary() > averageSalary.getAsDouble() * PropertiesUtil.MAX_RATE) {
                        resultSet.add(String.format(messageBundle.getString("salary_defferent.max_sallary"), employee.getId(), employee.getFirstName(), employee.getSecondName()));

                    }
                }

            } catch (NullPointerException e) {
                resultSet.add(String.format(messageBundle.getString("salary_defferent.empty_manager_salary"), employee.getId()));

            }
        }
        return resultSet;
    }

    private OptionalDouble getAverageSalary(Employee employee) {
        try {
            return employees.stream().filter(employee1 -> employee1.getManagerId() != null && employee1.getManagerId().equals(employee.getId())).mapToDouble(Employee::getSalary).average();
        } catch (NullPointerException e) {
            System.err.println(messageBundle.getString("salary_defferent.empty_employee_salary"));
        }
        return OptionalDouble.empty();
    }
}

