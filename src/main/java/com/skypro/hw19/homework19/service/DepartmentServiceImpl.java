package com.skypro.hw19.homework19.service;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.skypro.hw19.homework19.service.EmployeeService.employees;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    public Employee getMaxSalary(Integer job){
        Optional<Employee> emp = employees.values()
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
        Optional<Employee> emp = employees.values()
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
        return employees.values()
                .stream()
                .filter(v->v.getJob()==job)
                .collect(Collectors.toSet());
    }

    public Map<Integer,Set<Employee>> getAll(){
        return employees.values()
                .stream()
                .map(Employee::getJob)
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toMap(a->a, this::getAllByJob));
    }
}
