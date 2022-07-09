package com.example.bdefinition.factory;

import com.example.bdefinition.domain.User;

/**
 * @author ChunLei
 * @date 2022/7/9
 */
public class FooUserFactoryImpl implements UserFactory{

  @Override
  public User createUser(){
    User user = new User();
    user.setId(1L);
    user.setName("Foo");
    return user;
  }

}
