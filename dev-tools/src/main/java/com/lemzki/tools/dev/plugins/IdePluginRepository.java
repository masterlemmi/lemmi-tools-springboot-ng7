package com.lemzki.tools.dev.plugins;

import com.lemzki.tools.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdePluginRepository extends JpaRepository<IdePlugin, Long> {

}
