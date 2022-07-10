package com.example.bdefinition;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.context.support.GenericApplicationContext;

import com.example.bdefinition.domain.User;

/**
 * @author ChunLei
 * @date 2022/7/10
 */
public class BeanDefinitionRegistrationDemo {

  public static void main(String[] args) {
    // BeanDefinition
    BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
    definitionBuilder.addPropertyValue("id", 1L);
    definitionBuilder.addPropertyValue("name", "definitionBuilder");
    AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();

    // 方式1.BeanDefinitionRegistry 指定命名 beanName
    GenericApplicationContext applicationContext = new GenericApplicationContext();
    String beanRegName = "registerBeanDefinition";
    applicationContext.registerBeanDefinition(beanRegName, beanDefinition);
    applicationContext.refresh();
    User regBean = applicationContext.getBean(beanRegName, User.class);
    System.out.println(regBean);

    // 方式2.BeanDefinitionReaderUtils#registerWithGeneratedName 非命名方式
    String generatedName = BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, applicationContext);
    System.out.println("BeanDefinitionReaderUtils 注册生成的 bean name：" + generatedName);
    Object bean = applicationContext.getBean(generatedName);
    System.out.println(bean);

    //AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(applicationContext);

    applicationContext.close();
  }

}
