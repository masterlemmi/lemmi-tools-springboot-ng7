package com.lemzki.tools.security;

import com.lemzki.tools.security.model.User;
import com.lemzki.tools.security.UserRepository;
import com.lemzki.tools.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
    private UserRepository myUserRepository;


    @Override
    public User save(User user) {
     return myUserRepository.save(user);
    }

    @Override
    public Optional<User> findByGoogleId(String googleId) {
      return myUserRepository.findByGoogleId(googleId);

    }
}
