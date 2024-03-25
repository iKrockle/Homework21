package com.skypro.hw19.homework19;

import java.util.Objects;

public class Employee {
    private final String firstName ;

    private final String lastName;

    private final Integer job;
    private final Double salary;

    public Employee(String firstName, String lastName, Integer job, Double salary)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getJob(){
        return job;
    }

    public double getSalary(){
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "{\"firstName\" : \"" + getFirstName() + "\", \"lastName\" : \"" + getLastName() +
                "\", \"job\" : \"" +getJob()+"\", \"salary\" : \""+getSalary()+"\"}";
    }
}
