package com.example.bdefinition.domain;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.Data;

/**
 * @author ChunLei
 * @date 2022/7/9
 */
@Data
public class Foo implements InitializingBean, DisposableBean {

  private String name;

  @PostConstruct
  public void post() {
    System.out.println("Foo#post()");
  }

  public void doPost() {
    System.out.println("Foo#doPost()");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("Foo#preDesstroy()");
  }

  public void doDestroy() {
    System.out.println("Foo#doDestroy()");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("Foo implements InitializingBean#afterPropertiesSet()");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("Foo implements DisposableBean#destroy()");
  }

}
