package com.example.bdefinition.factory;

import com.example.bdefinition.domain.User;

/**
 * @author ChunLei
 * @date 2022/7/9
 */
public interface UserFactory {
  default User createUser() {
    return User.createUser();
  }
}
