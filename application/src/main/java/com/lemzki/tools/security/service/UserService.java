package com.lemzki.tools.security.service;

import com.lemzki.tools.security.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findByGoogleId(String googleId);

}
