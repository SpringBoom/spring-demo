package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junying
 * @date 2024/5/21
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

  @GetMapping("")
  public String orderPage() {
    return "Hello World";
  }

}
