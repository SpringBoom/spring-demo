package com.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MenuRepositoryTest {

	@Autowired
	DataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MenuRepository menuRepository;

	@Test
	void testSelect(){
		Long l = menuRepository.countMenuItems();
		System.out.println(l);
	}

	@Test
	void testDataSource(){
		System.out.println(dataSource);
		System.out.println(jdbcTemplate.getDataSource());
		System.out.println(jdbcTemplate);
	}

}