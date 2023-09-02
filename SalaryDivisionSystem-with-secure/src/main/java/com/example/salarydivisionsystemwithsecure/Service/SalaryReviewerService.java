package com.example.salarydivisionsystemwithsecure.Service;

import com.example.salarydivisionsystemwithsecure.Model.*;
import com.example.salarydivisionsystemwithsecure.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryReviewerService {
    private final SalaryReviewerRepository salaryReviewerRepository;
    private final FinancialCommitmentRepository financialCommitmentRepository;
    private final SalaryOwnerRepository salaryOwnerRepository;
    private final EntertainmentRepository entertainmentRepository;
    private final SalaryDivisionSummeryRepo salaryDivisionSummeryRepo;
    public List<FinancialCommitment> getAllOwnerFinancialCommitment(Integer user_id) {
        SalaryReviewer salaryReviewer=salaryReviewerRepository.findSalaryReviewerById(user_id);
        SalaryOwner salaryOwner=salaryOwnerRepository.findSalaryOwnerById(salaryReviewer.getSalaryOwners().getId());
        return financialCommitmentRepository.findFinancialCommitmentBySalaryOwner(salaryOwner);
    }

    public List<Entertainment> getAllOwnerEntertainment(Integer user_id) {
        SalaryReviewer salaryReviewer=salaryReviewerRepository.findSalaryReviewerById(user_id);
        SalaryOwner salaryOwner=salaryOwnerRepository.findSalaryOwnerById(salaryReviewer.getSalaryOwners().getId());
        return entertainmentRepository.findAllBySalaryOwner(salaryOwner);
    }

    public List<SalaryDivisionSummery> getAllOwnerSalaryDivisionSummery(Integer user_id) {
        SalaryReviewer salaryReviewer=salaryReviewerRepository.findSalaryReviewerById(user_id);
        SalaryOwner salaryOwner=salaryOwnerRepository.findSalaryOwnerById(salaryReviewer.getSalaryOwners().getId());
        return salaryDivisionSummeryRepo.findAllBySalaryOwners(salaryOwner);
    }
}
