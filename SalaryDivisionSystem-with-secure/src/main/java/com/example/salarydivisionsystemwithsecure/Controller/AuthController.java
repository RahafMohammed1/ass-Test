package com.example.salarydivisionsystemwithsecure.Controller;

import com.example.salarydivisionsystemwithsecure.Api.ApiResponse;
import com.example.salarydivisionsystemwithsecure.DTO.AuthDto;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register/owner")
    public ResponseEntity salaryOwnerRegister(@Valid @RequestBody AuthDto authDto)
    {
        authService.salaryOwnerRegister(authDto);
        return ResponseEntity.status(200).body(new ApiResponse("the Salary owner is registered"));
    }
    //Just the Salary Owner who has the Authority to add the reviewer.
    @PostMapping("/register/reviewer")
    public ResponseEntity salaryReviewerRegister(@AuthenticationPrincipal User user, @Valid @RequestBody AuthDto authDto)
    {
        authService.salaryReviewerRegister(authDto, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("the user is registered"));
    }

    @GetMapping("/logout")
    public ResponseEntity logout()
    {
        return ResponseEntity.status(200).body("Log out");
    }
}
