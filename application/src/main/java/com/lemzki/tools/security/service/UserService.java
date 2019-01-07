package com.lemzki.tools.security.service;

import com.lemzki.tools.security.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
