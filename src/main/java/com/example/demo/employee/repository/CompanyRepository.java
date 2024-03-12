package com.example.demo.employee.repository;

import com.example.demo.employee.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(value = "select c from Company c where id < ?1 order by id desc limit ?2")
    List<Company> findNextWithPageCursor(Integer afterId, int limit);

    @Query(value = "select c from Company c where id > ?1 order by id desc limit ?2")
    List<Company> findPreviousWithPageCursor(Integer beforeId, int limit);

    @Query(value = "select c from Company c order by id desc limit ?1")
    List<Company> findWithCursor(int limit);

}
