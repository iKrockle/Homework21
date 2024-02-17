package com.skypro.hw19.homework19.service;

import com.skypro.hw19.homework19.Employee;

import java.util.HashMap;
import java.util.Map;

public interface EmployeeService {
    Map<String,Employee> employees = new HashMap<>();
    int maxEmployees = 5;

    Employee addEmployee(String firstName, String lastName,Integer job,Integer salary);
    Employee delEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
}
