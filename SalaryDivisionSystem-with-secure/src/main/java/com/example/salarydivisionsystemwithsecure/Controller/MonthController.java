package com.example.salarydivisionsystemwithsecure.Controller;

import com.example.salarydivisionsystemwithsecure.Api.ApiResponse;
import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Service.MonthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/month")
public class MonthController {
    private final MonthService monthService;
    @GetMapping("/get-all")
    public ResponseEntity getAllMyMonth(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(monthService.getAllMyMonth(user.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addMonth(@AuthenticationPrincipal User user, @Valid @RequestBody Month month)
    {
        monthService.addMonth(user.getId(),month);
        return ResponseEntity.status(200).body(new ApiResponse("the Month is added"));
    }
    @PutMapping("/update/{month_id}")
    public ResponseEntity updateMonth(@AuthenticationPrincipal User user, @Valid @RequestBody Month month, @PathVariable Integer month_id)
    {
        monthService.updateMonth(user.getId(),month,month_id);
        return ResponseEntity.status(200).body(new ApiResponse("the month is updated"));
    }
    @DeleteMapping("/delete/{month_id}")
    public ResponseEntity deleteMonth(@AuthenticationPrincipal User user,@PathVariable Integer month_id)
    {
        monthService.deleteMonth(user.getId(),month_id);
        return ResponseEntity.status(200).body(new ApiResponse("the month is deleted"));
    }
}
