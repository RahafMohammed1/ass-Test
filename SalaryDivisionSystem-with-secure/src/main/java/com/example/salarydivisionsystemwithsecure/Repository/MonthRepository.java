package com.example.salarydivisionsystemwithsecure.Repository;

import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthRepository extends JpaRepository<Month,Integer> {
    List<Month> findAllBySalaryOwner(SalaryOwner salaryOwner);
    Month findMonthById(Integer id);
}
