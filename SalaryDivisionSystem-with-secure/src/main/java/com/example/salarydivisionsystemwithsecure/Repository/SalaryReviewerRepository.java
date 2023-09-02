package com.example.salarydivisionsystemwithsecure.Repository;

import com.example.salarydivisionsystemwithsecure.Model.SalaryReviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryReviewerRepository extends JpaRepository<SalaryReviewer,Integer> {
SalaryReviewer findSalaryReviewerById(Integer id);
}
