package com.skypro.hw19.homework19.service;

import com.skypro.hw19.homework19.Employee;

import java.util.Collection;

public interface EmployeeService {
    int maxEmployees = 1;
    Employee addEmployee(String firstName, String lastName,Integer job,Double salary);
    Employee delEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    Collection<Employee> getAll();
}
