package com.lemzki.tools.dev.shortcuts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class IdeShortcutControllerImpl implements IdeShortcutController {

    @Autowired
    IdeShortcutService service;

    @Override
    public List<IdeShortcut> getAllShortcuts() {
        return service.getAllShortcuts();
    }
}
