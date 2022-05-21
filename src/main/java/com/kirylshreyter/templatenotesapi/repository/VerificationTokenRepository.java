package com.kirylshreyter.templatenotesapi.repository;

import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
