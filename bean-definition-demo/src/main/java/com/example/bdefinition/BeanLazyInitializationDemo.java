package com.example.bdefinition;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import com.example.bdefinition.domain.User;

/**
 * 延迟初始化，lazy bean 在上下文初始化的时候不会被创建，只有 BeanDefinition 被定义。
 * 获取 bean 实例大概步骤：
 * <ul>1.getSingleton(beanName); 返回 null</ul>
 * <ul>2.getBeanDefinition(beanName);</ul>
 * <ul>3.createBean</ul>
 *
 * @author ChunLei
 * @date 2022/7/10
 */
public class BeanLazyInitializationDemo {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(BeanLazyInitializationDemo.class);
    System.out.println("ApplicationContext get lazyUser");
    // lazy initialized create bean at get
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
