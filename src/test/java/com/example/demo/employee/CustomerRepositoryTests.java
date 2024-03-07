package com.example.demo.employee;

import com.example.demo.employee.entity.Customer;
import com.example.demo.employee.entity.Employee;
import com.example.demo.employee.repository.CustomerRepository;
import com.example.demo.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CustomerRepositoryTests {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        assert customerRepository != null;
    }

    @Test
    public void test_insert_customer(){
        //
        Employee employee = employeeRepository.findByEmail("tom@example.com");
        assert employee != null;
        //
        LocalDate localDate = LocalDate.parse("1990-01-01", formatter);
        Customer customer = Customer.builder()
                .name("sam")
                .email("sam@example.com")
                .employee(employee)
                .dateOfBirth(localDate)
                .status(1)
                .build();
        //
        customerRepository.save(customer);
        assert customer.getId() != null;
        //
        Optional<Customer> employeeOptional = customerRepository.findById(customer.getId());
        assert employeeOptional.isPresent();
    }

    @Test
    public void test_find_by_email(){
        Optional<Customer> customer = customerRepository.findByEmail("lucas@example.com");
        //
        System.out.println(customer.get().getEmployee());
    }

    @Test
    public void test_find_by_email2(){
        Optional<Customer> customer = customerRepository.findByEmail("lucas@example.com");
        //
        System.out.println(customer.get().getEmail());
    }

    @Test
    public void test_delete_by_email(){
        Optional<Customer> customer = customerRepository.findByEmail("lucas@example.com");
        assert customer.isPresent();
        //
        customerRepository.deleteById(customer.get().getId());
    }

    @Test
    public void test_delete_by_email2(){
        Optional<Customer> customer = customerRepository.findByEmail("sam@example.com");
        assert customer.isPresent();
        //
        Customer customer1 = customer.get();
        customer1.setStatus(9);
        customer1.setDeletedBy(1000);
        customer1.setDeletedTime(LocalDateTime.now());
        customerRepository.delete(customer1);
        //
    }

    @Test
    public void test_insert_customer_list(){
        //
        Employee employee = employeeRepository.findByEmail("tom@example.com");
        assert employee != null;
        //
        LocalDate localDate = LocalDate.parse("1990-01-01", formatter);
        List<Customer> list = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            Customer customer = Customer.builder()
                    .name("lucas" + i)
                    .email("lucas@example.com")
                    .dateOfBirth(localDate)
                    .status(1)
                    .employee(employee)
                    .build();
            //
            list.add(customer);
        }
        //
        LocalTime start = LocalTime.now();
        List<Customer> customers = customerRepository.saveAll(list);
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

    @Test
    public void test_find_by_name(){
        List<Customer> customerList = customerRepository.findByNameEndsWith("lucas%");
        System.out.println(customerList.size());
        assert customerList.size() > 0;
    }

    @Test
    public void test_find_by_name_pageable(){
        Pageable pageable = Pageable.ofSize(10);
        Page<Customer> customerPage = customerRepository.findByName("lucas1%", pageable);
        System.out.println(customerPage.getTotalPages() + "," + customerPage.getTotalElements());
    }

    @Test
    public void test_find_by_name_pageable2(){
        Pageable pageable = Pageable.ofSize(10).withPage(1);
        Page<Customer> customerPage = customerRepository.findByName("lucas1%", pageable);
        System.out.println(customerPage.getTotalPages() + "," + customerPage.getTotalElements());
    }

    @Test
    public void test_find_by_employee_pageable(){
        Pageable pageable = Pageable.ofSize(10).withPage(1);
        Page<Customer> customerPage = customerRepository.findByEmployee(603, pageable);
        System.out.println(customerPage.getTotalPages() + "," + customerPage.getTotalElements());
    }

}
