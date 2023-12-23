package com.example.txd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoService {
    public static final String SQL = "insert into t_demo (name, create_time, update_time) values(?, now(), now())";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public String showNames() {
        return String.join(",", jdbcTemplate.queryForList("select name from t_demo", String.class));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRecordRequired() {
        jdbcTemplate.update(SQL, "one");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRecordRequiredNew(){
        jdbcTemplate.update(SQL,"two");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertRecordNested(){
        jdbcTemplate.update(SQL,"three");
        throw new RuntimeException();
    }

}
