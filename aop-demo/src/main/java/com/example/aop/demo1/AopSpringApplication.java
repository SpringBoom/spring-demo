package com.example.aop.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring AOP demo
 * <p/>
 * 需要加 @Configuration 否则 @EnableAspectJAutoProxy 不会生效；或者是将这两个注解加到上下文的范围内
 */
@Configuration
@EnableAspectJAutoProxy
public class AopSpringApplication {
  public static void main(String[] args) {
    String contextPackage = AopSpringApplication.class.getPackage().getName();
    System.out.println(contextPackage);
    // 使用 annotation 上下文
    ApplicationContext context = new AnnotationConfigApplicationContext(contextPackage);
    Performance bean = context.getBean(Performance.class);
    bean.perform();
    bean.perform(2);
  }
}
