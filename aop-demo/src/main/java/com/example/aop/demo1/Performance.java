package com.example.aop.demo1;

import org.springframework.stereotype.Component;

/**
 * @author ChunLei
 * @date 2022/7/2
 */
@Component
public class Performance {
  public void perform(){
    System.out.println("Performance.perform()");
  }
}
