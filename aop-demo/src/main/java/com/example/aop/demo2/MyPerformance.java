package com.example.aop.demo2;

import org.springframework.stereotype.Component;

/**
 * 待增强的 bean。MyPerformance并没有实现 {@link Encoreable} 接口，却可以通过切面引入其功能
 *
 * @author ChunLei
 * @date 2022/7/3
 */
@Component
public class MyPerformance {

}
