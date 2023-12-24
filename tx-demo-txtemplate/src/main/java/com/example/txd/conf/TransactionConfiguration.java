package com.example.txd.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class TransactionConfiguration {
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 手动创建一个 TransactionTemplate；SpringBoot 在 TransactionAutoConfiguration 中包含了一个内部类
     * TransactionTemplateConfiguration，会自动基于明确的PlatformTransactionManager 创建 TransactionTemplate。
     *
     * @param transactionManager
     * @return
     */
    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }
}
