package com.skypro.hw19.homework19.controller;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.exception.EmployeeNotFoundException;
import com.skypro.hw19.homework19.service.DepartmentServiceImpl;

import com.skypro.hw19.homework19.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentsControllerTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final int departmentId = 1;
    private final Employee employee1 = new Employee("Ivan", "Ivanov", departmentId, 80.0);
    private final Employee employee2 = new Employee("Petr", "Petrov", departmentId,60.0);
    private final Employee employee3 = new Employee("Oleg", "Olegov", 2, 30.0);
    private final List<Employee> allEmployees = Arrays.asList(employee1, employee2, employee3);
    @Test
    void findMaxInDep_success() {
        when(employeeService.getAll()).thenReturn(allEmployees);
        double expectedMaxSalary = employee1.getSalary();

        double actualMaxSalaryInFirstDep = departmentService.getMaxSalary(departmentId);
        assertEquals(expectedMaxSalary, actualMaxSalaryInFirstDep);
        assertTrue(employee1.getSalary() >= employee2.getSalary());
    }

    @Test
    void findMinInDep_success() {
        when(employeeService.getAll()).thenReturn(allEmployees);
        double expectedMinSalary = employee2.getSalary();

        double actualMinSalary = departmentService.getMinSalary(departmentId);
        assertEquals(expectedMinSalary, actualMinSalary);
        assertTrue(employee1.getSalary() >= employee2.getSalary());
    }

    @Test
    void getMaxSalary_withEmployeeNotFoundException() {
        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Сотрудник с максимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.getMaxSalary(departmentId));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getMinSalary_withEmployeeNotFoundException() {
        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.getMinSalary(departmentId));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getSalarySum_success() {
        when(employeeService.getAll()).thenReturn(allEmployees);
        double expectedSalarySum = employee1.getSalary() + employee2.getSalary();

        double actualSalarySum = departmentService.getSalarySum(departmentId);
        assertEquals(departmentId, employee1.getJob());
        assertEquals(departmentId, employee2.getJob());
        assertEquals(expectedSalarySum, actualSalarySum);
    }

    @Test
    void getAllByDep() {
        when(employeeService.getAll()).thenReturn(allEmployees);
        List<Employee> expectedEmployeesFromFirstDep = Arrays.asList(employee1, employee2);

        List<Employee> actualEmployeesFromFirstDep = new ArrayList<>(departmentService.getAllByJob(departmentId));
        assertEquals(departmentId, employee1.getJob());
        assertEquals(departmentId, employee2.getJob());
        assertEquals(expectedEmployeesFromFirstDep, actualEmployeesFromFirstDep);
    }

    @Test
    void getAllSorted() {
        when(employeeService.getAll()).thenReturn(allEmployees);
        Map<Integer, List<Employee>> expectedGroupedEmployees = new HashMap<>();
        expectedGroupedEmployees.put(departmentId, Arrays.asList(employee1, employee2));
        expectedGroupedEmployees.put(employee3.getJob(), Collections.singletonList(employee3));

        Map<Integer, List<Employee>> actualGroupedEmployees = departmentService.getAllSorted();
        assertEquals(expectedGroupedEmployees, actualGroupedEmployees);
    }
}