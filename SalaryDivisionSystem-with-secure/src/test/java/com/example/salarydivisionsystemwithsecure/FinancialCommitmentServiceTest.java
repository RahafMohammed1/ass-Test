package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Model.FinancialCommitment;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Repository.FinancialCommitmentRepository;
import com.example.salarydivisionsystemwithsecure.Repository.MonthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryDivisionSummeryRepo;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryOwnerRepository;
import com.example.salarydivisionsystemwithsecure.Service.FinancialCommitmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FinancialCommitmentServiceTest {
    @InjectMocks
    FinancialCommitmentService financialCommitmentService;

    @Mock
    FinancialCommitmentRepository financialCommitmentRepository;
    @Mock
    SalaryOwnerRepository salaryOwnerRepository;
    @Mock
    MonthRepository monthRepository;
    @Mock
    SalaryDivisionSummeryRepo salaryDivisionSummeryRepo;

    User user;

    SalaryOwner salaryOwner;

    FinancialCommitment financialCommitment1;
    FinancialCommitment financialCommitment2;
    FinancialCommitment financialCommitment3;

    List<FinancialCommitment> financialCommitments;

    @BeforeEach
    void SetUp()
    {
        user=new User(null,"RahafMohammed11","1234","OWNER",null,null);
        salaryOwner=new SalaryOwner(null,"Rahaf",user,null,null,null,null,null);
        financialCommitment1=new FinancialCommitment(null,1,"saling",100,"not complete",salaryOwner,null);
        financialCommitment2=new FinancialCommitment(null,1,"teeting",100,"not complete",salaryOwner,null);
        financialCommitment3=new FinancialCommitment(null,1,"booling",100,"not complete",salaryOwner,null);
        financialCommitments=new ArrayList<>();
        financialCommitments.add(financialCommitment1);
        financialCommitments.add(financialCommitment2);
        financialCommitments.add(financialCommitment3);
    }
    @Test
    public void getAllMyFinancialCommitment()
    {
        when(salaryOwnerRepository.findSalaryOwnerById(user.getId())).thenReturn(salaryOwner);
        when(financialCommitmentRepository.findFinancialCommitmentBySalaryOwner(salaryOwner)).thenReturn(financialCommitments);

        List<FinancialCommitment> financialCommitmentList=financialCommitmentService.getAllMyFinancialCommitment(user.getId());
        Assertions.assertEquals(financialCommitmentList,financialCommitments);

        verify(salaryOwnerRepository,times(1)).findSalaryOwnerById(user.getId());
        verify(financialCommitmentRepository,times(1)).findFinancialCommitmentBySalaryOwner(salaryOwner);

    }

}
