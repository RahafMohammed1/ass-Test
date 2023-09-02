package com.example.salarydivisionsystemwithsecure.Service;

import com.example.salarydivisionsystemwithsecure.Api.ApiException;
import com.example.salarydivisionsystemwithsecure.Model.FinancialCommitment;
import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.SalaryDivisionSummery;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Repository.MonthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryDivisionSummeryRepo;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryDivisionSummeryService {
    private final SalaryDivisionSummeryRepo salaryDivisionSummeryRepo;
    private final SalaryOwnerRepository salaryOwnerRepository;
    private final MonthRepository monthRepository;
    public List<SalaryDivisionSummery> getAllSalaryDivisionSummery(Integer user_id) {
        SalaryOwner salaryOwner = salaryOwnerRepository.findSalaryOwnerById(user_id);
        return salaryDivisionSummeryRepo.findAllBySalaryOwners(salaryOwner);
    }

    public void addSalaryDivisionSummery(Integer user_id,SalaryDivisionSummery salaryDivisionSummery,Integer month_id)
    {
        SalaryOwner salaryOwner = salaryOwnerRepository.findSalaryOwnerById(user_id);
        Month month=monthRepository.findMonthById(month_id);
        salaryDivisionSummery.setMonth(month);
        salaryDivisionSummery.setSalaryOwners(salaryOwner);
        salaryDivisionSummery.setTheAmountOfMoneyForSaving(calculateSavingMoney(salaryDivisionSummery.getSalaryAmount()));
        salaryDivisionSummeryRepo.save(salaryDivisionSummery);
        salaryDivisionSummery.setTheAmountOfExcessMoney(salaryDivisionSummery.getSalaryAmount()-salaryDivisionSummery.getTheAmountOfMoneyForSaving());
        salaryDivisionSummeryRepo.save(salaryDivisionSummery);
    }
    public void updateSalaryDivisionSummery(Integer user_id,SalaryDivisionSummery salaryDivisionSummery,Integer salaryDivisionSummery_id)
    {
        SalaryDivisionSummery salaryDivisionSummery1=salaryDivisionSummeryRepo.findSalaryDivisionSummeryById(user_id);
        if (salaryDivisionSummery1 == null) {
            throw new ApiException("the salary Division Summery not found");
        }
        if(salaryDivisionSummery1.getSalaryOwners().getId()!=user_id)
        {
            throw new ApiException("this salary Division Summery not belong to you");
        }
        salaryDivisionSummery1.setMonth(salaryDivisionSummery.getMonth());
        salaryDivisionSummery1.setSalaryAmount(salaryDivisionSummery.getSalaryAmount());
        salaryDivisionSummery1.setMonth_number(salaryDivisionSummery.getMonth_number());
        salaryDivisionSummeryRepo.save(salaryDivisionSummery1);
    }

    public double calculateSavingMoney(double Salary)
    {
        double saving =(0.2*Salary);
        return saving;
    }
    public void deleteSalaryDivisionSummery(Integer user_id,Integer financialCommitment_id) {
        SalaryDivisionSummery salaryDivisionSummery1=salaryDivisionSummeryRepo.findSalaryDivisionSummeryById(user_id);
        if (salaryDivisionSummery1 == null) {
            throw new ApiException("the salary Division Summery not found");
        }
        if(salaryDivisionSummery1.getSalaryOwners().getId()!=user_id)
        {
            throw new ApiException("this salary Division Summery not belong to you");
        }
        salaryDivisionSummeryRepo.delete(salaryDivisionSummery1);
    }


}
