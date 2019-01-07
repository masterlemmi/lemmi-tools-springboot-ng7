package com.lemzki.tools.security.repo;

import com.lemzki.tools.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}