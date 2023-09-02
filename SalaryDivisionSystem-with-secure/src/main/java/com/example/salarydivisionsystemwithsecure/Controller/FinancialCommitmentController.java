package com.example.salarydivisionsystemwithsecure.Controller;

import com.example.salarydivisionsystemwithsecure.Api.ApiResponse;
import com.example.salarydivisionsystemwithsecure.Model.FinancialCommitment;
import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Service.FinancialCommitmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/financial-commitment")
public class FinancialCommitmentController {
    private final FinancialCommitmentService financialCommitmentService;
    @GetMapping("/get-all")
    public ResponseEntity getAllMyFinancialCommitment(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(financialCommitmentService.getAllMyFinancialCommitment(user.getId()));
    }

    @PostMapping("/add/{month_id}")
    public ResponseEntity addFinancialCommitment(@AuthenticationPrincipal User user, @Valid @RequestBody FinancialCommitment financialCommitment,@PathVariable Integer month_id)
    {
        financialCommitmentService.addFinancialCommitment(user.getId(),financialCommitment,month_id);
        return ResponseEntity.status(200).body(new ApiResponse("the FinancialCommitment is added"));
    }
    @PutMapping("/update/{financialCommitment_id}")
    public ResponseEntity updateFinancialCommitment(@AuthenticationPrincipal User user, @Valid @RequestBody FinancialCommitment financialCommitment, @PathVariable Integer financialCommitment_id)
    {
        financialCommitmentService.updateFinancialCommitment(user.getId(),financialCommitment,financialCommitment_id);
        return ResponseEntity.status(200).body(new ApiResponse("the financial Commitment is updated"));
    }

    @DeleteMapping("/delete/{financialCommitment_id}")
    public ResponseEntity deleteFinancialCommitment(@AuthenticationPrincipal User user, @PathVariable Integer financialCommitment_id)
    {
        financialCommitmentService.deleteFinancialCommitment(user.getId(),financialCommitment_id);
        return ResponseEntity.status(200).body(new ApiResponse("the financial Commitment is deleted"));
    }
    @PutMapping("/paycommitment/{financialCommitment_id}")
    public ResponseEntity payCommitment(@AuthenticationPrincipal User user,@PathVariable Integer financialCommitment_id) {
        financialCommitmentService.payCommitment(user.getId(),financialCommitment_id);
        return ResponseEntity.status(200).body(new ApiResponse("the commitment is has been paid"));
    }
}
