package com.prime_cell.back_end.repositories;

import com.prime_cell.back_end.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByName(String name);
    User findByPassword(String password);
}