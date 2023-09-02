package com.example.salarydivisionsystemwithsecure.Service;

import com.example.salarydivisionsystemwithsecure.Api.ApiException;
import com.example.salarydivisionsystemwithsecure.Model.FinancialCommitment;
import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Repository.MonthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthService {
   private final MonthRepository monthRepository;
    private final SalaryOwnerRepository salaryOwnerRepository;

    public List<Month> getAllMyMonth(Integer user_id) {
        SalaryOwner salaryOwner = salaryOwnerRepository.findSalaryOwnerById(user_id);
        return monthRepository.findAllBySalaryOwner(salaryOwner);
    }
    public void addMonth(Integer user_id, Month month)
   {
       SalaryOwner salaryOwner = salaryOwnerRepository.findSalaryOwnerById(user_id);
       month.setSalaryOwner(salaryOwner);
       monthRepository.save(month);
   }

   public void updateMonth(Integer user_id, Month month,Integer month_id)
   {
       Month month1=monthRepository.findMonthById(month_id);
       if(month1==null)
       {
           throw new ApiException("the month not found");
       }
       if(month1.getSalaryOwner().getId()!=user_id)
       {
           throw new ApiException("this month not belong to you");
       }
       month1.setMonth_number(month.getMonth_number());
       monthRepository.save(month1);
   }

    public void deleteMonth(Integer user_id,Integer month_id)
    {
        Month month1=monthRepository.findMonthById(month_id);
        if(month1==null)
        {
            throw new ApiException("the month not found");
        }
        if(month1.getSalaryOwner().getId()!=user_id)
        {
            throw new ApiException("this month not belong to you");
        }
        monthRepository.delete(month1);
    }

}
