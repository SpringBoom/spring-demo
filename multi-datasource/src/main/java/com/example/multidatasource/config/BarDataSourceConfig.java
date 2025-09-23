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
public class BarDataSourceConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.bar.datasource")
  public DataSourceProperties barDataSourceProperties() {
    log.info("barDataSourceProperties");
    return new DataSourceProperties();
  }

  @Bean
  public DataSource barDataSource(DataSourceProperties barDataSourceProperties) {
    log.info("barDataSource: {}", barDataSourceProperties);
    return barDataSourceProperties.initializeDataSourceBuilder().build();
  }

  @Bean
  public PlatformTransactionManager barTransactionManager(DataSource barDataSource) {
    return new DataSourceTransactionManager(barDataSource);
  }

  @Bean
  public JdbcTemplate barJdbcTemplate(DataSource barDataSource) {
    log.info("barJdbcTemplate: {}", barDataSource);
    return new JdbcTemplate(barDataSource);
  }
}
