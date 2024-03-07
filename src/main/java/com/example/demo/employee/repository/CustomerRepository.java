package com.example.demo.employee.repository;

import com.example.demo.employee.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

    @Query("select u from Customer u where u.name like %?1")
    List<Customer> findByNameEndsWith(String name);

    @Query(value = "SELECT * FROM customer WHERE name like %?1",
            countQuery = "SELECT count(*) FROM customer WHERE name like %?1",
            nativeQuery = true)
    Page<Customer> findByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM customer WHERE employee_id = ?1",
            countQuery = "SELECT count(*) FROM customer WHERE employee_id = ?1",
            nativeQuery = true)
    Page<Customer> findByEmployee(Integer employeeId, Pageable pageable);

}
