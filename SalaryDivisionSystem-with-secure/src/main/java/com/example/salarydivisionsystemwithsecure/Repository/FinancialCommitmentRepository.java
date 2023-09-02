package com.example.salarydivisionsystemwithsecure.Repository;

import com.example.salarydivisionsystemwithsecure.Model.FinancialCommitment;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialCommitmentRepository extends JpaRepository<FinancialCommitment,Integer> {

    List<FinancialCommitment> findFinancialCommitmentBySalaryOwner(SalaryOwner salaryOwner);
    FinancialCommitment findFinancialCommitmentById(Integer id);

}
