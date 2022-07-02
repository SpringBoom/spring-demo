package com.example.aop.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring AOP demo
 */
@Configuration
@EnableAspectJAutoProxy
public class AopSpringApplication {
  public static void main(String[] args) {
    String contextPackage = AopSpringApplication.class.getPackage().getName();
    System.out.println(contextPackage);
    ApplicationContext context = new AnnotationConfigApplicationContext(contextPackage);
    Performance bean = context.getBean(Performance.class);
    bean.perform();
  }
}
