package com.lemzki.tools.security;

import com.lemzki.tools.security.model.Role;
import com.lemzki.tools.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GoogleAuthoritiesExtractor implements AuthoritiesExtractor {
  @Autowired
  public UserService userService;

  @Autowired
  RoleService roleService;

  @Override
  public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
    String id = (String) map.get("id");
    Optional<User> user = userService.findByGoogleId(id);

    //user not registered to our DB so no authorities given
    if (!user.isPresent()) {
      return Collections.emptyList();
    }

    Set<Role> roleSet = roleService.findByUsers(user.get());
    String[] roles = roleSet.stream()
        .map(Role::getName)
        .toArray(String[]::new);

    return AuthorityUtils.createAuthorityList(roles);
  }
}
