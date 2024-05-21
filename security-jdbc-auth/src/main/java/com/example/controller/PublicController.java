package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junying
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/public")
public class PublicController {

  @GetMapping("/hello")
  public String hello() {
    return "Public Hello World";
  }
  @GetMapping("/tologin")
  public String tologin() {
    return "You need to login first!";
  }
}
