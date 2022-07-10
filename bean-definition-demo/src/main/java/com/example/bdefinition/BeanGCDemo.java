package com.example.bdefinition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.bdefinition.domain.User;

/**
 * @author ChunLei
 * @date 2022/7/10
 */
public class BeanGCDemo {

  public static void main(String[] args) throws InterruptedException {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanGCDemo.class);
    User user = applicationContext.getBean("user", User.class);
    System.out.println(user);
    applicationContext.close();
    // gc 触发 bean finalize() 方法，并不一定
    Thread.sleep(10000L);
    System.gc();
    Thread.sleep(10000L);
  }

  @Bean
  public User user() {
    User user = new User();
    user.setId(1L);
    user.setName("hah");
    return user;
  }

}
