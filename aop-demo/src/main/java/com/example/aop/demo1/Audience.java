package com.example.aop.demo1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * AOP
 *
 * @author ChunLei
 * @date 2022/7/2
 */
@Aspect
@Component
public class Audience {

  @Before("execution( * com.example.aop.demo1.Performance.perform(..))")
  public void before() {
    System.out.println("before");
  }

}
