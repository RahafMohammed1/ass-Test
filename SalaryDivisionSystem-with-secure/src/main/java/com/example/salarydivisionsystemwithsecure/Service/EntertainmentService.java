package com.example.salarydivisionsystemwithsecure.Service;

import com.example.salarydivisionsystemwithsecure.Api.ApiException;
import com.example.salarydivisionsystemwithsecure.Model.*;
import com.example.salarydivisionsystemwithsecure.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntertainmentService {
    private final SalaryOwnerRepository salaryOwnerRepository;
    private final EntertainmentRepository entertainmentRepository;
    private final MonthRepository monthRepository;
    private final SalaryDivisionSummeryRepo salaryDivisionSummeryRepo;

    public List<Entertainment> getAllMyEntertainment(Integer user_id) {
        SalaryOwner salaryOwner = salaryOwnerRepository.findSalaryOwnerById(user_id);
        return entertainmentRepository.findAllBySalaryOwner(salaryOwner);
    }

    public void addEntertainment(Integer user_id,Entertainment entertainment,Integer month_id)
    {
        SalaryOwner salaryOwner = salaryOwnerRepository.findSalaryOwnerById(user_id);
        Month month=monthRepository.findMonthById(month_id);
        entertainment.setMonth(month);
        entertainment.setSalaryOwner(salaryOwner);
        entertainmentRepository.save(entertainment);
    }

    public void updateEntertainment(Integer user_id,Entertainment entertainment,Integer entertainment_id)
    {
        Entertainment entertainment1=entertainmentRepository.findEntertainmentById(entertainment_id);
        if (entertainment1 == null) {
            throw new ApiException("the Entertainment not found");
        }
        if(entertainment1.getSalaryOwner().getId()!=user_id)
        {
            throw new ApiException("this Entertainment not belong to you");
        }
        entertainment1.setTitle(entertainment.getTitle());
        entertainment1.setAmountOfMoney(entertainment.getAmountOfMoney());
        entertainment1.setMonthNumber(entertainment.getMonthNumber());
        entertainmentRepository.save(entertainment1);
    }

    public void deleteEntertainment(Integer user_id,Integer entertainment_id) {
        Entertainment entertainment1=entertainmentRepository.findEntertainmentById(entertainment_id);
        if (entertainment1 == null) {
            throw new ApiException("the Entertainment not found");
        }
        if(entertainment1.getSalaryOwner().getId()!=user_id)
        {
            throw new ApiException("this Entertainment not belong to you");
        }
        entertainmentRepository.delete(entertainment1);
    }
    public void WithdrawMoneyFromOneOfTheEntertainment(Integer user_id,Integer entertainment_id,Integer amount)
    {
        Entertainment entertainment1=entertainmentRepository.findEntertainmentById(entertainment_id);
        if (entertainment1 == null) {
            throw new ApiException("the Entertainment not found");
        }
        if(entertainment1.getSalaryOwner().getId()!=user_id)
        {
            throw new ApiException("this Entertainment not belong to you");
        }
        Month month=monthRepository.findMonthById(entertainment1.getMonth().getId());
        SalaryDivisionSummery salaryDivisionSummery=salaryDivisionSummeryRepo.findSalaryDivisionSummeryByMonth(month);
        if(salaryDivisionSummery==null)
        {
            throw new ApiException("Please enter your salary first for this month");
        }
        if(salaryDivisionSummery.getSalaryOwners().getId()!=user_id)
        {
            throw new ApiException("Please enter your salary first for this month");
        }
        if(salaryDivisionSummery.getTheAmountOfExcessMoney()<entertainment1.getAmountOfMoney())
        {
            throw new ApiException("Error,You have not enough of money ");
        }
        entertainment1.setAmountOfMoney(entertainment1.getAmountOfMoney()-amount);
        double c=salaryDivisionSummery.getTheAmountOfExcessMoney()-amount;
        double a=salaryDivisionSummery.getTheAmountOfMoneyForEntertainment()+amount;
        salaryDivisionSummery.setTheAmountOfMoneyForEntertainment(a);
        salaryDivisionSummery.setTheAmountOfExcessMoney(c);
        salaryDivisionSummeryRepo.save(salaryDivisionSummery);
        entertainmentRepository.save(entertainment1);
    }


}
