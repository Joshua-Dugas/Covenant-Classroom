package com.dugas.covenantclassroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Controller
public class HomeController {
  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  @GetMapping({ "/", "/index", "/home" })
  public String index() {
    logger.info("Home page accessed");
    return "Index";
  }
}
