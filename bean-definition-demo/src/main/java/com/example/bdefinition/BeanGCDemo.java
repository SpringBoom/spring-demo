package com.example.bdefinition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.bdefinition.domain.Foo;
import com.example.bdefinition.domain.User;

/**
 * @author ChunLei
 * @date 2022/7/10
 */
public class BeanGCDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanGCDemo.class);
    System.out.println("applicationContext close");
    applicationContext.close();
    System.out.println("applicationContext closed");
    System.out.println("before System gc");
    // gc 触发 bean finalize() 方法
    System.gc();
    System.out.println("after System gc");
  }

  @Bean
  public User user() {
    User user = new User();
    user.setId(1L);
    user.setName("hah");
    return user;
  }

}
