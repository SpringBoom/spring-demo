package com.example.multidatasource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

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
  @ConfigurationProperties(prefix = "spring.datasource.foo")
  public DataSourceProperties fooDataSourceProperties() {
    log.info("fooDataSourceProperties");
    return new DataSourceProperties();
  }

  @Bean
  public DataSource fooDataSource(DataSourceProperties fooDataSourceProperties) {
    log.info("fooDataSource: {}", fooDataSourceProperties);
    return fooDataSourceProperties.initializeDataSourceBuilder().build();
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
