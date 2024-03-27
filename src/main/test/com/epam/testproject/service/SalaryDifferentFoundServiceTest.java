package com.epam.testproject.service;

import com.epam.testproject.dto.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SalaryDifferentFoundServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void shouldReturnMassage_WhenSalaryHasLessDifferentThanTwentyPercent() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 11000l, null));
        data.add(new Employee(2, "Anna5", "Smith5", 10000l, 1));
        data.add(new Employee(3, "Anna6", "Smith6", 10500l, 1));
        data.add(new Employee(4, "Anna6", "Smith6", 10700l, 1));

        //when
        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(data);
        List<String> result = salaryDifferentFoundService.foundManagersWhoEarnLess();

        //then
        assertEquals(1, result.size());
        assertEquals("Employee Anna1 Smith1 with ID 1 has not enough salary", result.get(0));

    }


    @Test
    public void shouldReturnMassage_WhenManagerSalaryBiggerThanTeamMembersAverageSalaryMoreThanFiftyPercent() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 200000l, null));
        data.add(new Employee(2, "Anna5", "Smith5", 10000l, 1));
        data.add(new Employee(3, "Anna6", "Smith6", 10500l, 1));
        data.add(new Employee(4, "Anna6", "Smith6", 10700l, 1));

        //when
        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(data);
        List<String> result = salaryDifferentFoundService.foundManagersWhoEarnLess();

        //then
        assertEquals(1, result.size());
        assertEquals("Employee Anna1 Smith1 with ID 1 has too mach salary then his team-members", result.get(0));

    }


    @Test
    public void shouldNotReturnMassage_WhenManagerSalaryInNeededRange() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 13000l, null));
        data.add(new Employee(2, "Anna5", "Smith5", 10000l, 1));
        data.add(new Employee(3, "Anna6", "Smith6", 10500l, 1));
        data.add(new Employee(4, "Anna6", "Smith6", 10700l, 1));

        //when
        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(data);
        List<String> result = salaryDifferentFoundService.foundManagersWhoEarnLess();

        //then
        assertEquals(0, result.size());
    }

    @Test
    public void shouldNotReturnMassage_WhenManagerSalaryInNeededRangeCheckBottomBorder() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 12000l, null));
        data.add(new Employee(2, "Anna5", "Smith5", 10000l, 1));
        data.add(new Employee(3, "Anna6", "Smith6", 10000l, 1));
        data.add(new Employee(4, "Anna6", "Smith6", 10000l, 1));

        //when
        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(data);
        List<String> result = salaryDifferentFoundService.foundManagersWhoEarnLess();

        //then
        assertEquals(0, result.size());
    }

    @Test
    public void shouldNotReturnMassage_WhenManagerSalaryInNeededRangeCheckUpBorder() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 15000l, null));
        data.add(new Employee(2, "Anna5", "Smith5", 10000l, 1));
        data.add(new Employee(3, "Anna6", "Smith6", 10000l, 1));
        data.add(new Employee(4, "Anna6", "Smith6", 10000l, 1));

        //when
        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(data);
        List<String> result = salaryDifferentFoundService.foundManagersWhoEarnLess();

        //then
        assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnErrorMassage_WhenDontFillSalary() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", null, null));
        data.add(new Employee(2, "Anna5", "Smith5", 10000l, 1));
        data.add(new Employee(3, "Anna6", "Smith6", 10000l, 1));
        data.add(new Employee(4, "Anna6", "Smith6", 10000l, 1));

        //when
        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(data);
        List<String> result = salaryDifferentFoundService.foundManagersWhoEarnLess();

        //then
        assertEquals(1, result.size());
        assertEquals("Don't fill salary for Employee with ID 1", result.get(0));

    }

    @Test
    public void shouldReturnConsoleErrorMassage_WhenDontFillSalaryInTeamMember() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 13000l, null));
        data.add(new Employee(2, "Anna5", "Smith5", null, 1));
        data.add(new Employee(3, "Anna6", "Smith6", 10000l, 1));
        data.add(new Employee(4, "Anna6", "Smith6", 10000l, 1));

        //when
        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(data);
        List<String> result = salaryDifferentFoundService.foundManagersWhoEarnLess();

        //then
        assertEquals("Don't correct file, don't fill salary field\r\n", errContent.toString());
        assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnEmptyReport_whenDoesntFoundRecords() {

        //given
        List<Employee> data = new ArrayList<>();

        //when
        SalaryDifferentFoundService salaryDifferentFoundService = new SalaryDifferentFoundService(data);
        List<String> result = salaryDifferentFoundService.foundManagersWhoEarnLess();

        //then
        assertEquals(0, result.size());

    }
}

