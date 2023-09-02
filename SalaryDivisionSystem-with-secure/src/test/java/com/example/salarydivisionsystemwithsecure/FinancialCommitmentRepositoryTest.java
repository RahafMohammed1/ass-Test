package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Model.FinancialCommitment;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Repository.AuthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.FinancialCommitmentRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryOwnerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FinancialCommitmentRepositoryTest {

    @Autowired
    FinancialCommitmentRepository financialCommitmentRepository;
    @Autowired
    SalaryOwnerRepository salaryOwnerRepository;

    @Autowired
    AuthRepository authRepository;

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

    }
    @Test
    public void findFinancialCommitmentBySalaryOwnerTest()
    {
        authRepository.save(user);
        salaryOwnerRepository.save(salaryOwner);
        financialCommitmentRepository.save(financialCommitment1);
        financialCommitmentRepository.save(financialCommitment2);
        financialCommitmentRepository.save(financialCommitment3);

        financialCommitments=financialCommitmentRepository.findFinancialCommitmentBySalaryOwner(salaryOwner);

        Assertions.assertThat(financialCommitments.get(0).getSalaryOwner().getId()).isEqualTo(salaryOwner.getId());
    }

    @Test
    public void findFinancialCommitmentById()
    {
        authRepository.save(user);
        salaryOwnerRepository.save(salaryOwner);
        financialCommitmentRepository.save(financialCommitment1);
        FinancialCommitment financialCommitment=financialCommitmentRepository.findFinancialCommitmentById(financialCommitment1.getId());
        Assertions.assertThat(financialCommitment).isEqualTo(financialCommitment1);
    }

}
