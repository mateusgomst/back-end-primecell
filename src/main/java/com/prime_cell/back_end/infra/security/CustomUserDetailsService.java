package com.prime_cell.back_end.infra.security;

import com.prime_cell.back_end.exceptions.AdminNotFoundException;
import com.prime_cell.back_end.models.User;
import com.prime_cell.back_end.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws AdminNotFoundException {
        User user = this.repository.findByEmail(username)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}