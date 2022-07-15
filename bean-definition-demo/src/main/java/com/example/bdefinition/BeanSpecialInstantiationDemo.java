package com.example.bdefinition;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.bdefinition.domain.User;
import com.example.bdefinition.factory.DefaultUserBeanImpl;
import com.example.bdefinition.factory.UserFactory;

/**
 * 特殊方式初始化 bean
 *
 * @author ChunLei
 * @date 2022/7/9
 */
public class BeanSpecialInstantiationDemo {

  public static void main(String[] args) throws Exception {

    // 1.直接使用 ServiceLoader
    ServiceLoader<UserFactory> serviceLoader1 =
        ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
    display(serviceLoader1);

    ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/special-bean-instantiation-context.xml");

    // 2.Spring 使用 ServiceLoader
    ServiceLoader serviceLoader = context.getBean("userFactoryServiceLoader", ServiceLoader.class);
    System.out.println(serviceLoader);
    display(serviceLoader);

    // 3.使用 AutowireCapableBeanFactory
    AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
    // 要使用具体实现类
    DefaultUserBeanImpl bean = beanFactory.createBean(DefaultUserBeanImpl.class);
    User user = bean.createUser();
    System.out.println(user);
  }

  private static void display(ServiceLoader<UserFactory> serviceLoader) {
    Iterator<UserFactory> iterator = serviceLoader.iterator();
    while (iterator.hasNext()) {
      UserFactory next = iterator.next();
      System.out.println(next);
      User user = next.createUser();
      System.out.println(user);
    }
    System.out.println();
  }

}
