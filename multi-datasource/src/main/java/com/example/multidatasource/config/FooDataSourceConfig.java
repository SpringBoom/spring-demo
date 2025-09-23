package com.example.multidatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 *
 * @author shing.shi
 * @since 2025/9/19
 */
@Slf4j
@Component
public class FooDataSourceConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.foo.datasource")
  public DataSourceProperties fooDataSourceProperties() {
    log.info("fooDataSourceProperties");
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.foo.datasource.hikari")
  DataSource fooDataSource(DataSourceProperties fooDataSourceProperties) {
    HikariDataSource fooHikariDataSource = fooDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    if (StringUtils.hasText(fooDataSourceProperties.getName())) {
      fooHikariDataSource.setPoolName(fooDataSourceProperties.getName());
    }
    return fooHikariDataSource;
  }

  @Bean
  public PlatformTransactionManager fooTransactionManager(DataSource fooDataSource) {
    return new DataSourceTransactionManager(fooDataSource);
  }

  @Bean
  public JdbcTemplate fooJdbcTemplate(DataSource fooDataSource) {
    log.info("fooJdbcTemplate: {}", fooDataSource);
    return new JdbcTemplate(fooDataSource);
  }
}
