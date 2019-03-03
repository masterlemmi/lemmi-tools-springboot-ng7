package com.lemzki.tools.dev.plugins;


import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface IdePluginController {

    static final String REST_BASE = "/api/dev-tools/ide";

    @GetMapping(REST_BASE + "/plugins")
    List<IdePlugin> getAllPlugins();


}
