package com.example.salarydivisionsystemwithsecure.Repository;

import com.example.salarydivisionsystemwithsecure.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {
  User findUserByUsername(String username);
}
