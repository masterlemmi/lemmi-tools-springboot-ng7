package com.lemzki.tools.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service public class RoleServiceImpl implements RoleService {


   @Autowired private RoleRepository myRoleRepository;


    @Override public Role save(Role user) {
        return myRoleRepository.save(user);
    }

    @Override public Set<Role> findByUsers(User user) {
        return myRoleRepository.findByUsers(user);

    }
}
