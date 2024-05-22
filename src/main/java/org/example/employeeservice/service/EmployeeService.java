package org.example.employeeservice.service;

import org.example.employeeservice.exception.EmployeeNotFoundException;
import org.example.employeeservice.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    public Employee getHighestPaidEmployee(List<Employee> employees) throws EmployeeNotFoundException {
        return Optional.ofNullable(employees)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employee found"));
    }

    public Employee getHighestPaidEmployeeInCity(List<Employee> employees, String city) throws EmployeeNotFoundException {
        return Optional.ofNullable(employees)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(x -> Objects.nonNull(x.getCity()))
                .filter(x -> x.getCity().equals(city))
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employee found"));
    }

    public List<String> getCitesWithMostEmployees(List<Employee> employees) {
        Map<String, Long> map = Optional.ofNullable(employees)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(x -> Objects.nonNull(x.getCity()))
                .collect(Collectors.groupingBy(Employee::getCity, Collectors.counting()));
        long max = map
                .values()
                .stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);
        return map
                .entrySet()
                .stream()
                .filter(x -> x.getValue().equals(max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public double getAverageSalaryByPosition(List<Employee> employees, String position) {
        return Optional.ofNullable(employees)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(x -> Objects.nonNull(x.getPosition()))
                .filter(x -> x.getPosition().equals(position))
                .map(Employee::getSalary)
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }

}
