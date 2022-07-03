package com.example.aop.demo1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定义切面。
 * <p/>
 * 切面也要是 Spring 的一个 bean
 * <p/>
 * 执行顺序为：
 * <ul>Around</ul>
 * <ul>Before</ul>
 * <ul>proceed()</ul>
 * <ul>Around</ul>
 * <ul>After</ul>
 * <ul>AfterReturning</ul>
 * <ul>AfterThrowing</ul>
 *
 * @author ChunLei
 * @date 2022/7/2
 */
@Aspect
@Component
public class MyAspect {

  /**
   * 定义切点。空的方法体，方法其实只是一个标识，供 @Pointcut 依赖
   */
  @Pointcut("execution( * com.example.aop.demo1.Performance.perform())")
  public void pointcut() {
  }

  @Before("pointcut()")
  public void before() {
    System.out.println("Before");
  }

  /**
   * 即便是抛异常也会执行 after，并且是要在 AfterThrowing 前执行
   */
  @After("pointcut()")
  public void after() {
    System.out.println("After");
  }

  /**
   * 在 After 之后执行
   */
  @AfterReturning("pointcut()")
  public void afterReturning() {
    System.out.println("AfterReturning");
  }

  @AfterThrowing("pointcut()")
  public void afterThrowing() {
    System.out.println("AfterThrowing");
  }

  @Around("pointcut()")
  public void around(ProceedingJoinPoint joinPoint) {
    System.out.println("Around before proceed");
    try {
      joinPoint.proceed();
      System.out.println("Around after proceed");
    } catch (Throwable e) {
      System.out.println("Around after throw exception");
      throw new RuntimeException(e);
    }
  }

  /**
   * 这里定义带有参数的切点
   * <p/>
   * TODO 切点的方法名不可以是重载的，会报错
   *
   * @param count
   */
  @Pointcut("execution( * com.example.aop.demo1.Performance.perform(int)) && args(count)")
  public void pointcut2(int count) {
  }

  @Before("pointcut2(count)")
  public void beforeWithParams(int count) {
    System.out.println("before with count: " + count);
  }

}
