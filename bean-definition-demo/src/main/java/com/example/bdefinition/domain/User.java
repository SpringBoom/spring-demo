package com.example.bdefinition.domain;

import lombok.Data;

/**
 * @author ChunLei
 * @date 2022/7/9
 */
@Data
public class User {

  private Long id;
  private String name;

  public static User createUser(){
    User user = new User();
    user.setId(1L);
    user.setName("User.createUser()");
    return user;
  }
}
