package com.skypro.hw19.homework19.service;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.exception.EmployeeAlreadyAddedException;
import com.skypro.hw19.homework19.exception.EmployeeBadParamException;
import com.skypro.hw19.homework19.exception.EmployeeNotFoundException;
import com.skypro.hw19.homework19.exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String,Employee> employees = new HashMap<>();
    @Override
    public Employee addEmployee(String firstName, String lastName,Integer job,Integer salary) {
        if (!StringUtils.isAlpha(firstName)||!StringUtils.isAlpha(firstName)){
            throw new EmployeeBadParamException("В параметрах содержится неподдерживаемые символы");
        }

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

    public Collection<Employee> getAll(){
        return employees.values();
    }
}
