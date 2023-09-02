package com.example.salarydivisionsystemwithsecure.Repository;

import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryOwnerRepository extends JpaRepository<SalaryOwner,Integer> {

    SalaryOwner findSalaryOwnerById(Integer id);



}
