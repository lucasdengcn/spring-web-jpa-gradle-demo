package com.example.demo.employee.repository;

import com.example.demo.employee.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EmployeeRepositoryTests {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        assert employeeRepository != null;
    }

    @Test
    public void test_insert_employee(){
        //
        LocalDate localDate = LocalDate.parse("1990-01-01", formatter);
        Employee employee = Employee.builder()
                .name("tom")
                .email("tom@example.com")
                .dateOfBirth(localDate)
                .status(1)
                .build();
        //
        employeeRepository.save(employee);
        assert employee.getId() != null;
        //
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        assert employeeOptional.isPresent();
    }

    @Test
    public void test_insert_employee_list(){
        //
        LocalDate localDate = LocalDate.parse("1990-01-01", formatter);
        List<Employee> list = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            Employee employee = Employee.builder()
                    .name("sam_" + i)
                    .email("sam@example.com")
                    .dateOfBirth(localDate)
                    .build();
            //
            list.add(employee);
        }
        //
        LocalTime start = LocalTime.now();
        List<Employee> employees = employeeRepository.saveAll(list);
        LocalTime end = LocalTime.now();
        //
        long duration = ChronoUnit.MILLIS.between(end, start);
        System.out.println("duration(ms): " + duration);
        // 602 ms
        //
//        employees.forEach(new Consumer<Employee>() {
//            @Override
//            public void accept(Employee employee) {
//                System.out.println(employee.getId());
//            }
//        });

    }


}
