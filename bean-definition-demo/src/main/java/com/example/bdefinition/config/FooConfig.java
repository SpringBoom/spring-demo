package com.example.bdefinition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.bdefinition.domain.Foo;

/**
 * @author ChunLei
 * @date 2022/7/9
 */
@Configuration
public class FooConfig {

  @Bean
  public Foo foo(){
    Foo foo = new Foo();
    foo.setName("FooConfig.foo");
    return foo;
  }

}
