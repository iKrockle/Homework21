package com.skypro.hw19.homework19.service;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.exception.EmployeeAlreadyAddedException;
import com.skypro.hw19.homework19.exception.EmployeeNotFoundException;
import com.skypro.hw19.homework19.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee addEmployee(String firstName, String lastName,Integer job,Integer salary) {
        Employee employee = new Employee(firstName,lastName,job,salary);
        if (employees.containsValue(employee)){
                throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }

        if (employees.size()==maxEmployees){
            throw new EmployeeStorageIsFullException("Штат уже полон");
        }

        employees.put(firstName+lastName,employee);
        return employee;
    }

    @Override
    public Employee delEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName,null,null);
        if (!employees.containsValue(employee)){
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(firstName+lastName);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName,null,null);
        if (!employees.containsValue(employee)){
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }else {
            return employee;
        }
    }

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
