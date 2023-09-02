package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Model.Entertainment;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Repository.AuthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.EntertainmentRepository;
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
public class EntertainmentRepositoryTest {

    @Autowired
    EntertainmentRepository entertainmentRepository;

    @Autowired
    SalaryOwnerRepository salaryOwnerRepository;

    @Autowired
    AuthRepository authRepository;

    User user;
    SalaryOwner salaryOwner;
    Entertainment entertainment1;
    Entertainment entertainment2;
    Entertainment entertainment3;

    List<Entertainment> entertainments;

    @BeforeEach
    void SetUp()
    {
        user=new User(null,"RahafMohammed11","1234","OWNER",null,null);
        salaryOwner=new SalaryOwner(null,"Rahaf",user,null,null,null,null,null);
        entertainment1=new Entertainment(null,1,"pay doll",50,salaryOwner,null);
        entertainment2=new Entertainment(null,1,"pay juz",50,salaryOwner,null);
        entertainment3=new Entertainment(null,1,"pay T-shirt",50,salaryOwner,null);

    }

    @Test
    public void findAllBySalaryOwnerTest()
    {
        authRepository.save(user);
        salaryOwnerRepository.save(salaryOwner);
        entertainmentRepository.save(entertainment1);
        entertainmentRepository.save(entertainment2);
        entertainmentRepository.save(entertainment3);

        entertainments=entertainmentRepository.findAllBySalaryOwner(salaryOwner);

        Assertions.assertThat(entertainments.get(0).getSalaryOwner().getId()).isEqualTo(salaryOwner.getId());
    }

    @Test
    public void findEntertainmentById()
    {
        authRepository.save(user);
        salaryOwnerRepository.save(salaryOwner);
        entertainmentRepository.save(entertainment1);
        Entertainment entertainment=entertainmentRepository.findEntertainmentById(entertainment1.getId());
        Assertions.assertThat(entertainment).isEqualTo(entertainment1);
    }

}
