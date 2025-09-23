package com.example.multidatasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
public class MultiDatasourceApplication {

  public static void main(String[] args) throws SQLException, InterruptedException {
    ConfigurableApplicationContext application = SpringApplication.run(MultiDatasourceApplication.class, args);
    DataSource fooDataSource = application.getBean("fooDataSource", DataSource.class);
    DataSource barDataSource = application.getBean("barDataSource", DataSource.class);
    log.info("fooDataSource: {}, getConnection: {}", fooDataSource, fooDataSource.getConnection());
    log.info("barDataSource: {}, getConnection: {}", barDataSource, barDataSource.getConnection());

    HikariDataSource fooHikariDataSource = (HikariDataSource) fooDataSource;
    log.info("fooHikariDataSource: {}", fooHikariDataSource);
    log.info("fooHikariDataSource getIdleTimeout: {}", fooHikariDataSource.getIdleTimeout());
    log.info("fooHikariDataSource getMinimumIdle: {}", fooHikariDataSource.getMinimumIdle());
    log.info("fooHikariDataSource getMaximumPoolSize: {}", fooHikariDataSource.getMaximumPoolSize());

    JdbcTemplate fooJdbcTemplate = application.getBean("fooJdbcTemplate", JdbcTemplate.class);
    Connection connection = fooJdbcTemplate.getDataSource().getConnection();
    log.info("connection: {}", connection);
    Thread.sleep(3000);
    Properties clientInfo = connection.getClientInfo();
    log.info("clientInfo: {}", clientInfo);
    String schema = connection.getSchema();
    log.info("schema: {}", schema);
    connection.close();
    Thread.sleep(3000);
    connection = fooJdbcTemplate.getDataSource().getConnection();
    log.info("connection: {}", connection);
    connection.close();

    fooJdbcTemplate.query("select 1", rs -> {
      log.info("rs: {}", rs);
      String string = rs.getString(1);
      log.info("0: {}", string);
    });
  }

}
