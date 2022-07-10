package com.example.bdefinition;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.example.bdefinition.domain.User;

/**
 * 注解 BeanDefinition
 *
 * @author ChunLei
 * @date 2022/7/10
 */
@Import(AnnotationBeanDefinitionDemo.MyBenConfig.class)
public class AnnotationBeanDefinitionDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 初始化
    applicationContext.register(AnnotationBeanDefinitionDemo.class);
    // Spring 不会重复注册 bean
    applicationContext.register(MyBenConfig.class);
    applicationContext.refresh();

    User user = applicationContext.getBean("user", User.class);
    System.out.println(user);

    Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
    System.out.println(beansOfType);

    applicationContext.close();
  }

  @Component
  public static class MyBenConfig {
    @Bean(name = {"user", "userBean"})
    public User user() {
      User user = new User();
      user.setId(1L);
      user.setName("@Bean");
      return user;
    }
  }

}
