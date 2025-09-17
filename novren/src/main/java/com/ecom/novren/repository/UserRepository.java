package com.ecom.novren.repository;

import com.ecom.novren.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
      User findByEmail(String email);
}
