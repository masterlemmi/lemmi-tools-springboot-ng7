package com.lemzki.tools.dev.shortcuts;


import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface IdeShortcutController {

    static final String REST_BASE = "/ide";

    @GetMapping(REST_BASE + "/shortcuts")
    List<IdeShortcut> getAllShortcuts();


}
