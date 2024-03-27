package com.epam.testproject.service;

import com.epam.testproject.dto.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HierarchyDeptServiceTest {


    @Test
    public void shouldReturnMessage_WhenHaveMoreThanFourManagerInHierarchy() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 2223l, null));
        data.add(new Employee(2, "Anna2", "Smith3", 2223l, 1));
        data.add(new Employee(3, "Anna3", "Smith3", 2223l, 2));
        data.add(new Employee(4, "Anna4", "Smith4", 2223l, 3));
        data.add(new Employee(5, "Anna5", "Smith5", 2223l, 4));
        data.add(new Employee(6, "Anna6", "Smith6", 2223l, 5));

        HierarchyDepthService hierarchyDepthService = new HierarchyDepthService(data);

        //when
        List<Employee> result = hierarchyDepthService.foundDepthHiearchy();


        //then
        assertEquals(1, result.size());
        assertEquals("Employee ID 6, Anna6 Smith6 has more than 4 managers.", result.get(0));
    }

    @Test
    public void shouldReturnMessage_WhenHaveTwoLoopElementsInHierarchy() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 2223l, null));
        data.add(new Employee(6, "Anna5", "Smith5", 2223l, 7));
        data.add(new Employee(7, "Anna6", "Smith6", 2223l, 6));

        HierarchyDepthService hierarchyDepthService = new HierarchyDepthService(data);

        //when
        List<Employee> result = hierarchyDepthService.foundDepthHiearchy();

        //then
        assertEquals(2, result.size());
        assertEquals("Cylic reference detected for employee with ID: 6.", result.get(0));
        assertEquals("Cylic reference detected for employee with ID: 7.", result.get(1));
    }

    @Test
    public void shouldNotReturnMessage_WhenHaveNotMoreThanFourManagersInHierarchy() {

        //given
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(1, "Anna1", "Smith1", 2223l, null));
        data.add(new Employee(2, "Anna2", "Smith3", 2223l, 1));
        data.add(new Employee(3, "Anna3", "Smith3", 2223l, 2));
        data.add(new Employee(4, "Anna4", "Smith4", 2223l, 3));

        HierarchyDepthService hierarchyDepthService = new HierarchyDepthService(data);

        //when
        List<Employee> result = hierarchyDepthService.foundDepthHiearchy();


        //then
        assertEquals(0, result.size());

    }

    @Test
    public void shouldReturnEmptyReport_whenDoesntFoundRecords() {

        //given
        List<Employee> data = new ArrayList<>();
        HierarchyDepthService hierarchyDepthService = new HierarchyDepthService(data);

        //when
        List<Employee> result = hierarchyDepthService.foundDepthHiearchy();


        //then
        assertEquals(0, result.size());

    }
    @Test
    public void shouldRetrunMessageWhenFoundDepthHierarchy() {
        // Test data
        List<Employee> testEmployees = new ArrayList<>(Arrays.asList(
                new Employee(1, "CEO", "One", 90000L, null),
                new Employee(2, "Anna", "Two", 50000L, 1),
                new Employee(3, "Helena", "Three", 55000L, 2),
                new Employee(4, "Helga", "Four", 45000L, 2),
                new Employee(5, "JuniorHelga", "Five", 40000L, 3),
                new Employee(6, "Junior", "Six", 40000L, 4),
                new Employee(7, "ManagerAnton", "Seven", 80000L, 3),
                new Employee(8, "EmployeeDina", "Eight", 50000L, 7),
                new Employee(9, "Employee", "Nine", 45000L, 5),
                new Employee(10, "Employee", "Ten", 45000L, 9)
        ));

        HierarchyDepthService service = new HierarchyDepthService(testEmployees);

        List<String> result = service.foundDepthHiearchy();

        assertEquals(1, result.size());
        assertEquals("Employee ID 10, Employee Ten has more than 4 managers.", result.get(0));
    }
}
