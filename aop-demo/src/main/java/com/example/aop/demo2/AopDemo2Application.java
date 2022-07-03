package com.example.aop.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.aop.demo1.Performance;

/**
 * Spring AOP demo2
 * <p/>
 * 通过注解让 bean 引入新的功能
 */
@Configuration
@EnableAspectJAutoProxy
public class AopDemo2Application {
  public static void main(String[] args) {
    String contextPackage = AopDemo2Application.class.getPackage().getName();
    System.out.println(contextPackage);
    // 使用 annotation 上下文
    ApplicationContext context = new AnnotationConfigApplicationContext(contextPackage);
    MyPerformance myPerformance = context.getBean(MyPerformance.class);
    // MyPerformance -> Encoreable
    Encoreable encoreable = (Encoreable) myPerformance;
    encoreable.encore();
  }
}
