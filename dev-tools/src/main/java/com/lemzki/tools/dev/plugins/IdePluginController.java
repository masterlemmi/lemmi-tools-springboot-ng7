package com.lemzki.tools.dev.plugins;


import com.lemzki.tools.base.RestBaseController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface IdePluginController extends RestBaseController {

    @GetMapping("/ide/plugins")
    List<IdePlugin> getAllPlugins();


}
