package com.example.bdefinition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.bdefinition.domain.User;

/**
 * 单例 bean 注册到应用上下文
 *
 * @author ChunLei
 * @date 2022/7/10
 */
public class SingleBeanRegistrationDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.refresh();

    // 通过 ConfigurableListableBeanFactory 注册实例
    // ConfigurableApplicationContext#getBeanFactory()
    ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
    User user = new User();
    user.setId(1L);
    user.setName("registerSingleton");
    beanFactory.registerSingleton("singletonBean", user);

    // 获取注册的实例
    Object singletonBean = context.getBean("singletonBean");
    System.out.println(singletonBean);
    System.out.println(user == singletonBean);

  }

}
