package com.skypro.hw19.homework19.service;

import com.skypro.hw19.homework19.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DepartmentService{
    Set<Employee> getAllByJob(Integer job);
    Map<Integer, List<Employee>> getAllSorted();
    Employee getMaxSalary(Integer job);
    Employee getMinSalary(Integer job);
}
