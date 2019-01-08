package com.lemzki.tools.security.service.service.impl;

import com.lemzki.tools.security.model.User;
import com.lemzki.tools.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}