package com.example.aop.demo1;

import org.springframework.stereotype.Component;

/**
 * 切入的业务对象
 *
 * @author ChunLei
 * @date 2022/7/2
 */
@Component
public class Performance {
  public void perform() {
    System.out.println("Performance.perform()");
    //throw new RuntimeException("test exception");
  }
}
