package com.skypro.hw19.homework19.controller;

import com.skypro.hw19.homework19.Employee;
import com.skypro.hw19.homework19.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/{id}/salary/max")
    public Double findMaxInDep(@RequestParam("job") Integer job) {
        return departmentService.getMaxSalary(job);
    }

    @GetMapping("/{id}/salary/min")
    public Double findMinInDep(@RequestParam("job") Integer job) {
        return departmentService.getMinSalary(job);
    }
    @GetMapping("/{id}/salary/sum")
    public Double getSalarySumByDepartment(@PathVariable Integer id) {
        return departmentService.getSalarySum(id);
    }

    @GetMapping("/{id}/employees")
    public Set<Employee> getAllByDep(@RequestParam("job") Integer job){
        return departmentService.getAllByJob(job);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllSorted(){
        return departmentService.getAllSorted();
    }
}
