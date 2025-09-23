package com.example;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariProxyConnection;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  public void contextLoads() throws SQLException {
    assertTrue(applicationContext.containsBean("dataSource"));
    Object dataSource = applicationContext.getBean("dataSource");
    assertTrue(dataSource instanceof DataSource);
    assertTrue(dataSource instanceof DruidDataSource);
    DruidDataSource druidPooledConnection = (DruidDataSource) dataSource;
    Connection connection = druidPooledConnection.getConnection();
    assertTrue(connection instanceof DruidPooledConnection);
    assertTrue("getMaxActive is not default, getMaxActive: " + druidPooledConnection.getMaxActive(),
        druidPooledConnection.getMaxActive() == DruidDataSource.DEFAULT_MAX_ACTIVE_SIZE);
  }

}
