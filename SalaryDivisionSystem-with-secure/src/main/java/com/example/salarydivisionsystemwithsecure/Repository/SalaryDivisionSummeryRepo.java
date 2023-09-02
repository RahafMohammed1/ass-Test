package com.example.salarydivisionsystemwithsecure.Repository;

import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.SalaryDivisionSummery;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryDivisionSummeryRepo extends JpaRepository<SalaryDivisionSummery,Integer> {
    SalaryDivisionSummery findSalaryDivisionSummeryByMonth(Month month);
    List<SalaryDivisionSummery> findAllBySalaryOwners(SalaryOwner salaryOwner);

    SalaryDivisionSummery findSalaryDivisionSummeryById(Integer id);
}
