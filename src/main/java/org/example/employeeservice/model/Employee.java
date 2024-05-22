package org.example.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Employee {

    private String name;
    private String surname;
    private String pesel;
    private double salary;
    private String position;
    private String city;

}
