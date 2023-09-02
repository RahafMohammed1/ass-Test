package com.example.salarydivisionsystemwithsecure.Controller;

import com.example.salarydivisionsystemwithsecure.Api.ApiResponse;
import com.example.salarydivisionsystemwithsecure.Model.FinancialCommitment;
import com.example.salarydivisionsystemwithsecure.Model.SalaryDivisionSummery;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Service.SalaryDivisionSummeryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/salary-division-summery")
public class SalaryDivisionSummeryController {
    private final SalaryDivisionSummeryService salaryDivisionSummeryService;
    @GetMapping("/get-all")
    public ResponseEntity getAllSalaryDivisionSummery(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(salaryDivisionSummeryService.getAllSalaryDivisionSummery(user.getId()));
    }
    @PostMapping("/add/{month_id}")
    public ResponseEntity addSalaryDivisionSummery(@AuthenticationPrincipal User user, @Valid @RequestBody SalaryDivisionSummery salaryDivisionSummery, @PathVariable Integer month_id)
    {
        salaryDivisionSummeryService.addSalaryDivisionSummery(user.getId(),salaryDivisionSummery,month_id);
        return ResponseEntity.status(200).body(new ApiResponse("the Salary Division Summery is added"));
    }
    @PutMapping("/update/{salaryDivisionSummery_id}")
    public ResponseEntity  updateSalaryDivisionSummery(@AuthenticationPrincipal User user, @Valid @RequestBody SalaryDivisionSummery salaryDivisionSummery, @PathVariable Integer salaryDivisionSummery_id)
    {
        salaryDivisionSummeryService.updateSalaryDivisionSummery(user.getId(),salaryDivisionSummery,salaryDivisionSummery_id);
        return ResponseEntity.status(200).body(new ApiResponse("the salary Division Summery is updated"));
    }
    @DeleteMapping("/delete/{salaryDivisionSummery_id}")
    public ResponseEntity deleteSalaryDivisionSummery(@AuthenticationPrincipal User user, @PathVariable Integer salaryDivisionSummery_id)
    {
        salaryDivisionSummeryService.deleteSalaryDivisionSummery(user.getId(),salaryDivisionSummery_id);
        return ResponseEntity.status(200).body(new ApiResponse("the salary Division Summery is deleted"));
    }

}

