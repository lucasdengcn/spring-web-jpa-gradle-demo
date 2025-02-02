package com.example.demo.employee.repository;

import com.example.demo.employee.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findFirstByEmail(String email);

    List<Customer> findTop10ByEmail(String email, Sort sort);

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

    @Transactional
    @Modifying
    @Query("update Customer u set u.email = :email, u.updatedTime = :now where u.id = :id")
    void updateEmail(@Param(value = "id") Integer id, @Param(value = "email") String email, @Param(value = "now") LocalDateTime now);

}
