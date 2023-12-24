package com.example.txd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.stream.Collectors;

@Service
public class DemoService {
    public static final String SQL = "insert into t_demo (name, create_time, update_time) values(?, now(), now())";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public String showNames() {
        return transactionTemplate.execute(status-> jdbcTemplate.queryForList("select name from t_demo",
                String.class).stream().collect(Collectors.joining(",")));
    }

    public void insertRecordRequired() {
        transactionTemplate.executeWithoutResult(status-> jdbcTemplate.update(SQL, "one"));
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertRecordNested(){
        jdbcTemplate.update(SQL,"three");
        throw new RuntimeException();
    }

}
