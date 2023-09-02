package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Repository.AuthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryOwnerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalaryOwnerRepositoryTest {

    @Autowired
    SalaryOwnerRepository salaryOwnerRepository;

    @Autowired
    AuthRepository authRepository;

    User user;

    SalaryOwner salaryOwner;


    @BeforeEach
    void SetUp() {
        user = new User(null, "RahafMohammed11", "1234", "OWNER", null, null);
        salaryOwner = new SalaryOwner(null, "Rahaf", user, null, null, null, null, null);
    }

    @Test
    public void findSalaryOwnerById()
    {
        authRepository.save(user);
        salaryOwnerRepository.save(salaryOwner);
        SalaryOwner salaryOwner1=salaryOwnerRepository.findSalaryOwnerById(salaryOwner.getId());
        Assertions.assertThat(salaryOwner1).isEqualTo(salaryOwner);

    }
}
