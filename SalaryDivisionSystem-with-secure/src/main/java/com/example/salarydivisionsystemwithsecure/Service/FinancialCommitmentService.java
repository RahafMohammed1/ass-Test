package com.example.salarydivisionsystemwithsecure.Service;

import com.example.salarydivisionsystemwithsecure.Api.ApiException;
import com.example.salarydivisionsystemwithsecure.Model.FinancialCommitment;
import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.SalaryDivisionSummery;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Repository.FinancialCommitmentRepository;
import com.example.salarydivisionsystemwithsecure.Repository.MonthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryDivisionSummeryRepo;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialCommitmentService {
    private final SalaryOwnerRepository salaryOwnerRepository;
    private final FinancialCommitmentRepository financialCommitmentRepository;
    private final MonthRepository monthRepository;
    private final SalaryDivisionSummeryRepo salaryDivisionSummeryRepo;
    public List<FinancialCommitment> getAllMyFinancialCommitment(Integer user_id) {
        SalaryOwner salaryOwner = salaryOwnerRepository.findSalaryOwnerById(user_id);
        return financialCommitmentRepository.findFinancialCommitmentBySalaryOwner(salaryOwner);
    }

    public void addFinancialCommitment(Integer user_id,FinancialCommitment financialCommitment,Integer month_id)
    {
        SalaryOwner salaryOwner = salaryOwnerRepository.findSalaryOwnerById(user_id);
        Month month=monthRepository.findMonthById(month_id);
        financialCommitment.setMonth(month);
        financialCommitment.setSalaryOwner(salaryOwner);
        financialCommitment.setStatus("not complete");
        financialCommitmentRepository.save(financialCommitment);
    }

    public void updateFinancialCommitment(Integer user_id,FinancialCommitment financialCommitment,Integer financialCommitment_id)
    {
        FinancialCommitment financialCommitment1=financialCommitmentRepository.findFinancialCommitmentById(financialCommitment_id);
        if (financialCommitment1 == null) {
            throw new ApiException("the FinancialCommitment not found");
        }
        if(financialCommitment1.getSalaryOwner().getId()!=user_id)
        {
            throw new ApiException("this FinancialCommitment not belong to you");
        }
        financialCommitment1.setTitleOfCommitment(financialCommitment.getTitleOfCommitment());
        financialCommitment1.setStatus(financialCommitment.getStatus());
        financialCommitment1.setMonth_number(financialCommitment.getMonth_number());
        financialCommitment1.setTotalValue(financialCommitment.getTotalValue());
        financialCommitmentRepository.save(financialCommitment1);
    }
    public void deleteFinancialCommitment(Integer user_id,Integer financialCommitment_id) {
        FinancialCommitment financialCommitment1 = financialCommitmentRepository.findFinancialCommitmentById(financialCommitment_id);
        if (financialCommitment1 == null) {
            throw new ApiException("the FinancialCommitment not found");
        }
        if (financialCommitment1.getSalaryOwner().getId() != user_id) {
            throw new ApiException("this FinancialCommitment not belong to you");
        }
        financialCommitmentRepository.delete(financialCommitment1);
    }

    public void payCommitment(Integer user_id,Integer financialCommitment_id)
    {
        FinancialCommitment financialCommitment1 = financialCommitmentRepository.findFinancialCommitmentById(financialCommitment_id);
        if (financialCommitment1 == null) {
            throw new ApiException("the FinancialCommitment not found");
        }
        if (financialCommitment1.getSalaryOwner().getId() != user_id) {
            throw new ApiException("this FinancialCommitment not belong to you");
        }
        String status=financialCommitment1.getStatus();
        if (status.equalsIgnoreCase("complete"))
        {
            throw new ApiException("Error,The Commitment has been paid");
        }
        Month month=monthRepository.findMonthById(financialCommitment1.getMonth().getId());
        SalaryDivisionSummery salaryDivisionSummery=salaryDivisionSummeryRepo.findSalaryDivisionSummeryByMonth(month);
        if(salaryDivisionSummery==null)
        {
            throw new ApiException("Please enter your salary first for this month");
        }
        if(salaryDivisionSummery.getSalaryOwners().getId()!=user_id)
        {
            throw new ApiException("Please enter your salary first for this month");
        }
        if(salaryDivisionSummery.getTheAmountOfExcessMoney()<financialCommitment1.getTotalValue())
        {
            throw new ApiException("Error,You have not enough of money ");
        }
        double salary=salaryDivisionSummery.getTheAmountOfExcessMoney();
        double salaryAfterPay=salary-financialCommitment1.getTotalValue();
        salaryDivisionSummery.setTheAmountOfExcessMoney(salaryAfterPay);
        salaryDivisionSummery.setTheAmountOfMoneyForFinancialCommitments(salaryDivisionSummery.getTheAmountOfMoneyForFinancialCommitments()+financialCommitment1.getTotalValue());
        salaryDivisionSummeryRepo.save(salaryDivisionSummery);
        financialCommitment1.setStatus("complete");
        financialCommitmentRepository.save(financialCommitment1);
    }
}
