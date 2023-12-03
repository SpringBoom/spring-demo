package com.example.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MenuRepository {

	private JdbcTemplate jdbcTemplate;

	public MenuRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Long countMenuItems(){
		return jdbcTemplate.queryForObject("select count(*) from t_menu",Long.class);
	}
}
