package com.lemzki.tools.dev.initializer;

import com.lemzki.tools.dev.enums.Resource;

public interface InitializerService {

    <T> T initResource(Resource resource);
}
