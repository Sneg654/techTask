package com.epam.testproject.service;

import com.epam.testproject.dto.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FillEmployeeServiceTest {
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
    public void shouldReturnConsoleErrorMassage_WhenCantFoundFIle() {
        //given
        FillEmployeeService fillEmployeeService = new FillEmployeeService();
        //when
        List<Employee> employeeList = fillEmployeeService.fillFromFile("test.csv");
        //then
        assertEquals(0, employeeList.size());
        assertEquals("Problem with reading from file\r\n", errContent.toString());

    }

    @Test
    public void shouldReturnEmptyList_WhenReadEmptyFile() {
        //given
        FillEmployeeService fillEmployeeService = new FillEmployeeService();
        //when
        List<Employee> employeeList = fillEmployeeService.fillFromFile("empty_file_test.csv");
        //then
        assertEquals(0, employeeList.size());
        assertEquals("", errContent.toString());

    }

    @Test
    public void shouldReturnListWithRecord_WhenReadFilledFile() {
        //given
        FillEmployeeService fillEmployeeService = new FillEmployeeService();
        //when
        List<Employee> employeeList = fillEmployeeService.fillFromFile("fill_file_with_1000_records.csv");
        //then
        assertEquals(1000, employeeList.size());

    }
}
