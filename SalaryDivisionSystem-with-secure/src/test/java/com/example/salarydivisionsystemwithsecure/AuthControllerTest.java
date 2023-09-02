package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Controller.AuthController;
import com.example.salarydivisionsystemwithsecure.Controller.EntertainmentController;
import com.example.salarydivisionsystemwithsecure.DTO.AuthDto;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Service.AuthService;
import com.example.salarydivisionsystemwithsecure.Service.EntertainmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AuthController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class AuthControllerTest {

    @MockBean
    AuthService authService;
    @Autowired
    MockMvc mockMvc;

    AuthDto authDto;

    @BeforeEach
    void SetUp() {
        authDto = new AuthDto("RahafMohammed11", "1234", "Rahaf");
    }
    @Test
    public void salaryOwnerRegisterTest()throws Exception
    {
        mockMvc.perform(post("/api/v1/auth/register/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authDto)))
                .andExpect(status().isOk());
    }



}
