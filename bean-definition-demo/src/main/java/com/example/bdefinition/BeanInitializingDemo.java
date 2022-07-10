package com.example.bdefinition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.bdefinition.domain.Foo;

/**
 * bean 初始化示例
 *
 * @author ChunLei
 * @date 2022/7/9
 */
@Configuration
public class BeanInitializingDemo {

  public static void main(String[] args) {
    // 1.@PostConstruct()
    AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
    annotationContext.register(BeanInitializingDemo.class);
    annotationContext.refresh();
    Foo foo1 = annotationContext.getBean("foo", Foo.class);

    // 2.XML init-method
    //ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/post-bean-context.xml");
    //Foo foo = context.getBean("foo", Foo.class);

    // 3. bean implements InitializingBean；注意Bar不是 lazy-init，所以是在创建应用上下文的时候初始化的
    System.out.println("ConfigurableApplicationContext 准备关闭");
    annotationContext.close();
    System.out.println("ConfigurableApplicationContext 已关闭");
  }

  @Bean(initMethod = "doPost", destroyMethod = "doDestroy")
  public Foo foo() {
    Foo foo = new Foo();
    foo.setName("BeanInitializingDemo@Bean");
    return foo;
  }

}
