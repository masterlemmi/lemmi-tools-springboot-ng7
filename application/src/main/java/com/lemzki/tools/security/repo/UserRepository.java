package com.lemzki.tools.security.repo;

import com.lemzki.tools.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
    User findByGoogleId (String googleId);
}