package com.epam.testproject.service;

import com.epam.testproject.dto.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FillEmployeeService {
    private static final String COMMA_DELIMITER = ",";
    private static final ResourceBundle messageBundle = ResourceBundle.getBundle("messages");
    public List<Employee> fillFromFile(String filePath)  {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {
                employees.add(getEmploeeFromFileLine(line.split(COMMA_DELIMITER)));
            }
            System.out.println(employees.size());

        } catch (IOException e) {
            System.err.println(messageBundle.getString("fill_employee.problem_reading"));
        }
        return employees;
    }

    private Employee getEmploeeFromFileLine(String[] values) {
        Employee employee = new Employee();
        employee.setId(Integer.valueOf(values[0]));
        employee.setFirstName(values[1]);
        employee.setSecondName(values[2]);
        employee.setSalary(Long.valueOf(values[3]));
        employee.setManagerId(Integer.valueOf(values[4]));
        return employee;
    }



}
