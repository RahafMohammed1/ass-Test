package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Repository.AuthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.MonthRepository;
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
public class MonthRepositoryTest {

    @Autowired
    MonthRepository monthRepository;

    @Autowired
    SalaryOwnerRepository salaryOwnerRepository;

    @Autowired
    AuthRepository authRepository;

    User user;

    SalaryOwner salaryOwner;
    Month month1;
    Month month2;
    Month month3;

    List<Month> months;

    @BeforeEach
    void SetUp()
    {
        user=new User(null,"RahafMohammed11","1234","OWNER",null,null);
        salaryOwner=new SalaryOwner(null,"Rahaf",user,null,null,null,null,null);
        month1=new Month(null,1,salaryOwner,null,null,null);
        month2=new Month(null,2,salaryOwner,null,null,null);
        month3=new Month(null,3,salaryOwner,null,null,null);
    }
    @Test
    public void findAllBySalaryOwnerTest()
    {
        authRepository.save(user);
        salaryOwnerRepository.save(salaryOwner);
        monthRepository.save(month1);
        monthRepository.save(month2);
        monthRepository.save(month3);

       months=monthRepository.findAllBySalaryOwner(salaryOwner);
       Assertions.assertThat(months.get(0).getSalaryOwner().getId()).isEqualTo(salaryOwner.getId());
    }

    @Test
    public void findMonthById()
    {
        authRepository.save(user);
        salaryOwnerRepository.save(salaryOwner);
        monthRepository.save(month1);
        Month month=monthRepository.findMonthById(month1.getId());
        Assertions.assertThat(month).isEqualTo(month1);
    }

}
