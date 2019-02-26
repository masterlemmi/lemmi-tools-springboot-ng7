package com.lemzki.tools.dev.plugins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class IdePluginControllerImpl implements  IdePluginController{

    @Autowired
    IdePluginService service;

    @Override
    public List<IdePlugin> getAllPlugins() {
        return service.getAllPlugins();
    }
}
