package com.lemzki.tools.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByUsers(User user);
}
