# AOP demo1

简单的 Spring AOP 使用

## 起步

1. maven dependency

```
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-aop</artifactId>
</dependency>
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjrt</artifactId>
</dependency>
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjweaver</artifactId>
</dependency>
```

## demo

### demo1

AOP 基础应用

### demo2

通过 `@DeclareParents` 如何给 bean 引入额外的接口实现