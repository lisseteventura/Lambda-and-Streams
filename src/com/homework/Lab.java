package com.homework;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {
    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    private <T> void printList(List<T> list) {
        for(Employee employees : employees) {
//            System.out.println(employees.getName() + " " + employees.getHireDate() + " " + employees.getSalary());
            System.out.println(employees);
        }
    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee> employeesOver50k =
            employees.stream()
                .filter(e -> e.getSalary() > 50_000)
                .collect(Collectors.toList());
            employeesOver50k.forEach(e -> System.out.println(e.getName() + " " + e.getSalary()));
    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<String> employeeNamesHiredAfter2012 =
                employees.stream()
                .filter(e -> e.getHireDate().isAfter(LocalDate.of(2012, 1,1)))
                        .map(e -> e.getName())
                         .collect(Collectors.toList());
            employeeNamesHiredAfter2012.forEach(e -> System.out.println(e));
    }

    @Test
    public void getMaxSalary() {
        double max = this.employees.stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0);
        System.out.println("Max salary: " + max);

    }

    @Test
    public void getMinSalary() {
        double min = this.employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);
        System.out.println("Min salary: " + min);

    }
    

    @Test
    public void getAverageSalaries() {
        double averageMale = this.employees.stream()
                .filter(e -> e.getGender().equals("Male"))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        double averageFemale = this.employees.stream()
                 .filter(e -> e.getGender().equals("Female"))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        System.out.println("Averages: Male: " + averageMale + " Female: " + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee highest = this.employees.stream()
                .reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2)
                .orElse(null);
        System.out.println(highest);
    }
}