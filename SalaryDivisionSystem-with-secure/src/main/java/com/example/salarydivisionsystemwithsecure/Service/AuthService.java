package com.example.salarydivisionsystemwithsecure.Service;

import com.example.salarydivisionsystemwithsecure.DTO.AuthDto;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.SalaryReviewer;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Repository.AuthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryOwnerRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryReviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final SalaryOwnerRepository salaryOwnerRepository;
    private final SalaryReviewerRepository salaryReviewerRepository;
    private final AuthRepository authRepository;

    public void salaryOwnerRegister(AuthDto authDto)

    {
        User user=new User();
        String hash= new BCryptPasswordEncoder().encode(authDto.getPassword());
        user.setPassword(hash);
        user.setUsername(authDto.getUsername());
        user.setRole("OWNER");
        authRepository.save(user);
        SalaryOwner salaryOwner=new SalaryOwner();
        salaryOwner.setName(authDto.getName());
        salaryOwner.setUser(user);
        salaryOwnerRepository.save(salaryOwner);
    }

  public void salaryReviewerRegister(AuthDto authDto,Integer owner_id)
  {
      User user=new User();
      String hash= new BCryptPasswordEncoder().encode(authDto.getPassword());
      user.setPassword(hash);
      user.setUsername(authDto.getUsername());
      user.setRole("REVIEWER");
      authRepository.save(user);
      SalaryReviewer salaryReviewer=new SalaryReviewer();
      SalaryOwner salaryOwner=salaryOwnerRepository.findSalaryOwnerById(owner_id);
      salaryReviewer.setName(authDto.getName());
      salaryReviewer.setUser(user);
      salaryReviewer.setSalaryOwners(salaryOwner);
      salaryReviewerRepository.save(salaryReviewer);
  }

}
