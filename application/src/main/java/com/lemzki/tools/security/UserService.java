package com.lemzki.tools.security;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findByGoogleId(String googleId);

}
