package com.lemzki.tools.security;

import java.util.Set;

public interface RoleService {
    Set<Role> findByUsers(User user);

}
