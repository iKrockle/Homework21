package com.skypro.hw19.homework19.controller;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public Employee findMaxInDep(@RequestParam("job") Integer job) {
        return departmentService.getMaxSalary(job);
    }

    @GetMapping("/min-salary")
    public Employee findMinInDep(@RequestParam("job") Integer job) {
        return departmentService.getMinSalary(job);
    }

    @GetMapping("/all-by-dep")
    public Set<Employee> getAllByDep(@RequestParam("job") Integer job){
        return departmentService.getAllByJob(job);
    }

    @GetMapping("/all")
    public Map<Integer,Set<Employee>> getAll(){
        return departmentService.getAll();
    }
}
