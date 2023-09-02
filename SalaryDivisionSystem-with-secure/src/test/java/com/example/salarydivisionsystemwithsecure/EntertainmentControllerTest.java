package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Controller.EntertainmentController;
import com.example.salarydivisionsystemwithsecure.Model.Entertainment;
import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Service.EntertainmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EntertainmentController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class EntertainmentControllerTest {

    @MockBean
    EntertainmentService entertainmentService;

    @Autowired
    MockMvc mockMvc;

    User user;

    Month month;
    SalaryOwner salaryOwner;
    Entertainment entertainment1;
    Entertainment entertainment2;
    Entertainment entertainment3;

    List<Entertainment> entertainments ,entertainmentList;
    @BeforeEach
    void SetUp()
    {
        user=new User(1,"RahafMohammed11","1234","OWNER",null,null);
        salaryOwner=new SalaryOwner(1,"Rahaf",user,null,null,null,null,null);
        month=new Month(1,1,salaryOwner,null,null,null);
        entertainment1=new Entertainment(1,1,"pay doll",50,salaryOwner,month);
        entertainment2=new Entertainment(2,1,"pay juz",50,salaryOwner,month);
        entertainment3=new Entertainment(3,1,"pay T-shirt",50,salaryOwner,month);

        entertainments= Arrays.asList(entertainment1);

        entertainmentList=Arrays.asList(entertainment2);
    }



    @Test
    public void addEntertainmentTest()throws Exception
    {
        mockMvc.perform(post("/api/v1/entertainment/add/{month_id}",month.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(entertainment1)))
                .andExpect(status().isOk());

    }

    @Test
    public void updateEntertainmentTest()throws Exception
    {

        mockMvc.perform(put("/api/v1/entertainment/update/{entertainment_id}",entertainment1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(entertainment1)))
                .andExpect(status().isOk());


    }
    @Test
    public void deleteEntertainment()throws Exception
    {

        mockMvc.perform(delete("/api/v1/entertainment/delete/{entertainment_id}",entertainment1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void WithdrawMoneyFromOneOfTheEntertainmentTest()throws Exception
    {
        Integer amount=5;
        mockMvc.perform(put("/api/v1/entertainment/withdraw/{entertainment_id}/{amount}",entertainment1.getId(),amount))
                .andExpect(status().isOk());

    }




}
