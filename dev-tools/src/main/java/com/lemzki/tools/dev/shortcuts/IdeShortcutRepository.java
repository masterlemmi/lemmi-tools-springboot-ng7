package com.lemzki.tools.dev.shortcuts;

import com.lemzki.tools.dev.plugins.IdePlugin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeShortcutRepository extends JpaRepository<IdeShortcut, Long> {
  
}
