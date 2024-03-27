package com.epam.testproject;

import com.epam.testproject.dto.Employee;
import com.epam.testproject.service.FillEmployeeService;
import com.epam.testproject.service.GenerateCSVFileService;
import com.epam.testproject.service.HierarchyDepthService;
import com.epam.testproject.service.SalaryDifferentFoundService;
import com.epam.testproject.util.PropertiesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Runner {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("dev");
    private static final String FILE_NAME = resourceBundle.getString("creator.file.name");

    public static void main(String[] args) {
        List<String> report = preparingReport();

        System.out.println("the report contains: " + report.size() + " records");
        System.out.println("____________________________________________________");
        report.forEach(System.out::println);

    }

    private static List<String> preparingReport() {
        GenerateCSVFileService generateCSVFileService = new GenerateCSVFileService();
        try {
            generateCSVFileService.generateFile(PropertiesUtil.FILE_NAME);
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file");
        }

        FillEmployeeService fillEmployeeService = new FillEmployeeService();
        List<Employee> employees = fillEmployeeService.fillFromFile(FILE_NAME);

        List<String> report = new ArrayList<>();

        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(employees);
        report.addAll(salaryDifferentFoundService.foundManagersWhoEarnLess());

        HierarchyDepthService hierarchyDepthService = new HierarchyDepthService(employees);
        report.addAll(hierarchyDepthService.foundDepthHiearchy());
        return report;

    }

}
