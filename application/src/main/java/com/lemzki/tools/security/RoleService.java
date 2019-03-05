package com.lemzki.tools.security;

import java.util.Set;

public interface RoleService {
    Role save(Role user);

    Set<Role> findByUsers(User user);

}
