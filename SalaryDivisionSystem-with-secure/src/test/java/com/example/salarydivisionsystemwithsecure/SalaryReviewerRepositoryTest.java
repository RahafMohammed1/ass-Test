package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.SalaryReviewer;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Repository.AuthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryReviewerRepository;
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
public class SalaryReviewerRepositoryTest {

    @Autowired
    SalaryReviewerRepository salaryReviewerRepository;

    @Autowired
    AuthRepository authRepository;

    User user;

    SalaryReviewer salaryReviewer;


    @BeforeEach
    void SetUp() {
        user = new User(null, "RahafMohammed11", "1234", "OWNER", null, null);
        salaryReviewer = new SalaryReviewer(null, "Rahaf", user, null);
    }
    @Test
    public void findSalaryReviewerById()
    {
        authRepository.save(user);
        salaryReviewerRepository.save(salaryReviewer);
        SalaryReviewer salaryReviewer1=salaryReviewerRepository.findSalaryReviewerById(salaryReviewer.getId());
        Assertions.assertThat(salaryReviewer1).isEqualTo(salaryReviewer);
    }


}
