package com.skypro.hw19.homework19.service;

import com.skypro.hw19.homework19.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DepartmentService{
    Set<Employee> getAllByJob(Integer job);
    Map<Integer, List<Employee>> getAllSorted();
    Double getMaxSalary(Integer job);
    Double getMinSalary(Integer job);
    Double getSalarySum(Integer job);
}
