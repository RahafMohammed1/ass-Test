package com.example.salarydivisionsystemwithsecure.Repository;

import com.example.salarydivisionsystemwithsecure.Model.Entertainment;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntertainmentRepository extends JpaRepository<Entertainment,Integer> {
    List<Entertainment> findAllBySalaryOwner(SalaryOwner salaryOwner);
    Entertainment findEntertainmentById(Integer id);
}
