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

    public Employee getMaxSalary(Integer job){
        Optional<Employee> emp = employeeService.getAll()
                .stream()
                .filter(v->v.getJob()==job)
                .max(Comparator.comparingInt(Employee::getSalary));
        Employee employee = emp.orElse(null);
        if (employee!=null){
            return employee;
        }
        else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Employee getMinSalary(Integer job){
        Optional<Employee> emp = employeeService.getAll()
                .stream()
                .filter(v->v.getJob()==job)
                .min(Comparator.comparingInt(Employee::getSalary));
        Employee employee = emp.orElse(null);
        if (employee!=null){
            return employee;
        }
        else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
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
