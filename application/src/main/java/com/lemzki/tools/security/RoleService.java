package com.lemzki.tools.security;

import com.lemzki.tools.security.model.Role;
import com.lemzki.tools.security.model.User;

import java.util.Set;

public interface RoleService {
    Role save(Role user);

    Set<Role> findByUsers(User user);

}
