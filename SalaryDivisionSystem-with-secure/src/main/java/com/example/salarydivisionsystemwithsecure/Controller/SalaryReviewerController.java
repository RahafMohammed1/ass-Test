package com.example.salarydivisionsystemwithsecure.Controller;

import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Service.FinancialCommitmentService;
import com.example.salarydivisionsystemwithsecure.Service.SalaryReviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reviewer")
public class SalaryReviewerController {
    private final SalaryReviewerService salaryReviewerService;
    @GetMapping("/get-all/financial-commitment")
    public ResponseEntity getAllOwnerFinancialCommitment(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(salaryReviewerService.getAllOwnerFinancialCommitment(user.getId()));
    }

    @GetMapping("/get-all/Entertainment")
    public ResponseEntity getAllOwnerEntertainment(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(salaryReviewerService.getAllOwnerEntertainment(user.getId()));

    }

    @GetMapping("/get-all/Summery")
    public ResponseEntity getAllSalaryDivisionSummery(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(salaryReviewerService.getAllOwnerSalaryDivisionSummery(user.getId()));
    }


}
