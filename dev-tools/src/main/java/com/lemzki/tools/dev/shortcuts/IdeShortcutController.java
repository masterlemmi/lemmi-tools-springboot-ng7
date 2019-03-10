package com.lemzki.tools.dev.shortcuts;


import com.lemzki.tools.base.RestBaseController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface IdeShortcutController extends RestBaseController {

    static final String REST_BASE = "/ide";

    @GetMapping(REST_BASE + "/shortcuts")
    List<IdeShortcut> getAllShortcuts();


}
