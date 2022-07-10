package com.example.bdefinition;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import com.example.bdefinition.domain.User;

/**
 * @author ChunLei
 * @date 2022/7/10
 */
public class BeanLazyInitializationDemo {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(BeanLazyInitializationDemo.class);
    System.out.println("ApplicationContext get lazyUser");
    Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
    Object lazyUser = applicationContext.getBean("lazyUser");
    System.out.println(lazyUser);
  }

  @Lazy
  @Bean(initMethod = "post")
  public User lazyUser() {
    System.out.println("lazyUser()");
    User user = new User();
    user.setId(1L);
    user.setName("LazyUser");
    return user;
  }

}
