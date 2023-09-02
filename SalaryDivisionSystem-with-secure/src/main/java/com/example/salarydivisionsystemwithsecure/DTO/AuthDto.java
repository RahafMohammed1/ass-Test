package com.example.salarydivisionsystemwithsecure.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Data
@AllArgsConstructor
public class AuthDto {
    @Column(columnDefinition = "Varchar (20) unique not null")
    @NotEmpty(message = "the username field is required")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Error,the username must be start with an alphabet ,All other characters can be alphabets, numbers or an underscore ,length 7-28 character")
    private String username;
    @NotEmpty(message = "the password field is required")
    private String password;
    @NotEmpty(message = "Please enter your name")
    private String name;
}
