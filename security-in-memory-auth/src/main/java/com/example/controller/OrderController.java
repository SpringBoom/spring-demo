package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junying
 * @date 2024/5/21
 */
@RestController
@RequestMapping("/order")
public class OrderController {

  @RequestMapping
  public String order(){
    return "Order Page";
  }

}
