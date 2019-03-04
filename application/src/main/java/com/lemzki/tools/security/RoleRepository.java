package com.lemzki.tools.security;

import com.lemzki.tools.security.model.Role;
import com.lemzki.tools.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByUsers(User user);
}
