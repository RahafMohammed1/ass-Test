package com.example.salarydivisionsystemwithsecure.Controller;

import com.example.salarydivisionsystemwithsecure.Api.ApiResponse;
import com.example.salarydivisionsystemwithsecure.Model.Entertainment;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Service.EntertainmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/entertainment")
public class EntertainmentController {
    private final EntertainmentService entertainmentService;
    @GetMapping("/get-all")
    public ResponseEntity getAllMyEntertainment(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(entertainmentService.getAllMyEntertainment(user.getId()));

    }
    @PostMapping("/add/{month_id}")
    public ResponseEntity addEntertainment(@AuthenticationPrincipal User user, @Valid @RequestBody Entertainment entertainment, @PathVariable Integer month_id)
    {
        entertainmentService.addEntertainment(user.getId(),entertainment,month_id );
        return ResponseEntity.status(200).body(new ApiResponse("the Entertainment is added"));
    }

    @PutMapping("/update/{entertainment_id}")
    public ResponseEntity updateEntertainment(@AuthenticationPrincipal User user, @Valid @RequestBody Entertainment entertainment, @PathVariable Integer  entertainment_id)
    {
        entertainmentService.updateEntertainment(user.getId(),entertainment,entertainment_id);
        return ResponseEntity.status(200).body(new ApiResponse("the Entertainment is updated"));
    }

    @DeleteMapping("/delete/{entertainment_id}")
    public ResponseEntity deleteEntertainment(@AuthenticationPrincipal User user, @PathVariable Integer entertainment_id)
    {
        entertainmentService.deleteEntertainment(user.getId(),entertainment_id);
        return ResponseEntity.status(200).body(new ApiResponse("the Entertainment is deleted"));
    }
    @PutMapping("/withdraw/{entertainment_id}/{amount}")
    public ResponseEntity WithdrawMoneyFromOneOfTheEntertainment(@AuthenticationPrincipal User user,@PathVariable Integer entertainment_id,@PathVariable Integer amount) {
        entertainmentService.WithdrawMoneyFromOneOfTheEntertainment(user.getId(),entertainment_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("the withdraw process is done"));
    }


}
