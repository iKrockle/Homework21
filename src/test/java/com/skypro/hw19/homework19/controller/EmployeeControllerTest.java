package com.skypro.hw19.homework19.controller;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.exception.EmployeeAlreadyAddedException;
import com.skypro.hw19.homework19.exception.EmployeeBadParamException;
import com.skypro.hw19.homework19.exception.EmployeeNotFoundException;
import com.skypro.hw19.homework19.exception.EmployeeStorageIsFullException;
import com.skypro.hw19.homework19.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    private final String firstName = "Ivan";
    private final String lastName = "Ivanov";
    private final double salary = 60000.40;
    private final int departmentId = 1;

    @BeforeEach
    public void restoreService() {
        employeeService = new EmployeeServiceImpl();
    }
    @Test
    void addEmployee() {
        Employee expectedEmployee = new Employee(firstName, lastName, departmentId, salary);

        Employee actualEmployee = employeeService.addEmployee(firstName, lastName, departmentId, salary);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void add_withEmployeeStorageIsFullException() {
        String expectedMessage = "Штат уже полон";
        employeeService.addEmployee("Petr", "Petrov", 1,56000.0);

        Exception exception = assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.addEmployee(firstName, lastName, departmentId, salary));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void add_withEmployeeAlreadyAddedException() {
        String expectedMessage = "Сотрудник уже существует";
        employeeService.addEmployee(firstName, lastName, departmentId,salary);

        Exception exception = assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee(firstName, lastName, departmentId, salary));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void add_withEmployeeBadParamException() {
        String expectedMessage = "В параметрах содержится неподдерживаемые символы";

        Exception exception = assertThrows(EmployeeBadParamException.class,
                () -> employeeService.addEmployee("Petr1", "Petrov", 1,56000.0));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void removeEmployee() {
        Employee expectedEmployee = new Employee(firstName, lastName, departmentId, salary);

        employeeService.addEmployee(firstName, lastName, departmentId, salary);

        //Начало теста
        Employee actualEmployee = employeeService.delEmployee(firstName, lastName);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void findEmployee() {
        Employee expectedEmployee = new Employee(firstName, lastName, departmentId, salary);

        employeeService.addEmployee(firstName, lastName, departmentId, salary);

        //Начало теста
        Employee actualEmployee = employeeService.findEmployee(firstName, lastName);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void remove_withEmployeeNotFoundException() {
        String expectedMessage = "Сотрудник не найден";
        employeeService.addEmployee("Petr", "Petrov", 1,56000.0);

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.delEmployee(firstName, lastName));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void find_withEmployeeNotFoundException() {
        String expectedMessage = "Сотрудник не найден";
        employeeService.addEmployee("Petr", "Petrov", 1,56000.0);

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.findEmployee(firstName, lastName));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getAll_success() {
        Collection<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(firstName, lastName, departmentId, salary));

        employeeService.addEmployee(firstName, lastName, departmentId, salary);

        Collection<Employee> actualEmployees = new ArrayList<>(employeeService.getAll());

        assertEquals(expectedEmployees, actualEmployees);
    }
}