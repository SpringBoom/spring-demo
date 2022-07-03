package com.example.aop.demo2;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Encoreable 切面
 *
 * @author ChunLei
 * @date 2022/7/3
 */
@Aspect
@Component
public class EncoreableIntroducer {

  /**
   * value：目标类，即那种bean要引入该接口，‘+’ 表示包含其子类
   * defaultImpl：具体提供的实现
   */
  @DeclareParents(value = "com.example.aop.demo2.MyPerformance+", defaultImpl = EncoreableDefaultImpl.class)
  public Encoreable encoreable;

}
