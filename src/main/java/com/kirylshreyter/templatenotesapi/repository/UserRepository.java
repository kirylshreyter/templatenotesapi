package com.kirylshreyter.templatenotesapi.repository;

import com.kirylshreyter.templatenotesapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
