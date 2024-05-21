package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;

/**
 * @author junying
 * @date 2024/5/21
 */
@Configuration
public class UrlControllerConfig {

  @Bean("/login")
  public UrlFilenameViewController loginController() {
    UrlFilenameViewController controller = new UrlFilenameViewController();
    controller.setSupportedMethods(HttpMethod.GET.name());
    controller.setSuffix(".html");
    return controller;
  }
}
