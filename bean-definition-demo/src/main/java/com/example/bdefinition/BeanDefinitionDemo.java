package com.example.bdefinition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import com.example.bdefinition.domain.User;

/**
 * 创建 BeanDefinition
 *
 * @author ChunLei
 * @date 2022/7/10
 */
public class BeanDefinitionDemo {

  public static void main(String[] args) {

    // 1.BeanDefinitionBuilder
    BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
    definitionBuilder.addPropertyValue("id", 1L);
    definitionBuilder.addPropertyValue("name", "definitionBuilder");
    AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();
    // TODO how to create bean by bean definition

    // 2.AbstractBeanDefinition 派生类 GenericBeanDefinition
    AbstractBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
    genericBeanDefinition.setBeanClass(User.class);
    MutablePropertyValues propertyValues = new MutablePropertyValues();
    propertyValues.add("id", 2L);
    propertyValues.add("name", "genericBeanDefinition");
    genericBeanDefinition.setPropertyValues(propertyValues);

  }

}
