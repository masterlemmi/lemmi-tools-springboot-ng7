package com.lemzki.tools.people.db.controller;

import org.springframework.web.bind.annotation.PostMapping;

public interface
LoginController {

    @PostMapping("/login")
    void login();
}
  