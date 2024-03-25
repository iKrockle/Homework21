package com.skypro.hw19.homework19.service;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Double getMaxSalary(Integer job){
        return employeeService.getAll()
                .stream()
                .filter(v->v.getJob()==job)
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден"));
    }

    public Double getMinSalary(Integer job){
        return employeeService.getAll()
                .stream()
                .filter(v->v.getJob()==job)
                .min(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден"));
    }

    public Double getSalarySum(Integer job) {
        return employeeService.getAll().stream()
                .filter(v->v.getJob()==job)
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
    }

    public Set<Employee> getAllByJob(Integer job){
        return employeeService.getAll()
                .stream()
                .filter(v->v.getJob()==job)
                .collect(Collectors.toSet());
    }

    public Map<Integer, List<Employee>> getAllSorted(){
        return employeeService.getAll().stream().collect(groupingBy(Employee::getJob));
    }
}
