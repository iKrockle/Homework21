package com.skypro.hw19.homework19.controller;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName
            ,@RequestParam("job") Integer job, @RequestParam("salary") Integer salary) {
        return employeeService.addEmployee(firstName,lastName,job,salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName) {
        return employeeService.delEmployee(firstName,lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName,lastName);
    }

    @GetMapping("/findMaxInDep")
    public Employee findMaxInDep(@RequestParam("job") Integer job) {
        return employeeService.getMaxSalary(job);
    }

    @GetMapping("/findMinInDep")
    public Employee findMinInDep(@RequestParam("job") Integer job) {
        return employeeService.getMinSalary(job);
    }

    @GetMapping("/allbydep")
    public Set<Employee> getAllByDep(@RequestParam("job") Integer job){
        return employeeService.getAllByJob(job);
    }

    @GetMapping("/all")
    public Map<Integer,Set<Employee>> getAll(){
        return employeeService.getAll();
    }
}
