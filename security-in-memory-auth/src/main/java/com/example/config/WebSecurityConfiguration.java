package com.example.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.sql.DataSource;

/**
 * @author junying
 * @date 2024/5/20
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * 使用内存保存用户信息；生产环境中倾向于使用 JdbcUserDetailsManager
   *
   * @param passwordEncoder
   * @return
   */
  @Bean
  public UserDetailsService userDetailsService(ObjectProvider<PasswordEncoder> passwordEncoder) {
    PasswordEncoder encoder = passwordEncoder.getIfAvailable(
        PasswordEncoderFactories::createDelegatingPasswordEncoder);

    UserDetails employee = User.builder()
        .username("lilei")
        .password("binarytea")
        .authorities("READ_ORDER", "WRITE_ORDER")
        .passwordEncoder(encoder::encode)
        .build();
    return new InMemoryUserDetailsManager(employee);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/public/*").permitAll()
        // 使用 HTTP Basic 认证
        .anyRequest().authenticated()
        .and().formLogin() // 使用表单登录
        .loginPage("/login").permitAll() // 设置登录页地址，全员可访问
        .defaultSuccessUrl("/order")
        .failureUrl("/login")
        .loginProcessingUrl("/doLogin")
        .usernameParameter("user")
        .passwordParameter("pwd")
        .and().httpBasic()
        .and().logout().logoutSuccessUrl("/login")
        .logoutRequestMatcher(new OrRequestMatcher(new AntPathRequestMatcher("/logout", "GET"),
            new AntPathRequestMatcher("/logout", "POST")));

  }
}
