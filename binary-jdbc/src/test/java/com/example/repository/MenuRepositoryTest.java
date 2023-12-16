package com.example.repository;

import com.example.model.MenuItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testSelect() {
        Long l = menuRepository.countMenuItems();
        System.out.println(l);
    }

    @Test
    void testDataSource() {
        System.out.println(dataSource);
        System.out.println(jdbcTemplate.getDataSource());
        System.out.println(jdbcTemplate);
    }

    @Test
    void testCountMenuItems() {
        Long l = menuRepository.countMenuItems();
        assertEquals(5, l);
    }

    @Test
    void testQueryItem() {
        MenuItem menuItem = menuRepository.queryForItem(1L);
        log.info("{}", menuItem);
        assertEquals(1L, menuItem.getId());
        assertEquals("Java咖啡", menuItem.getName());
        assertEquals("中杯", menuItem.getSize());
        assertEquals(BigDecimal.valueOf(1000), menuItem.getPrice());
    }

    @Test
    void testNamedParameter() {
        MenuItem addItem = MenuItem.builder().name("addtest").size("big").price(BigDecimal.valueOf(1000)).build();
        MenuItem i = menuRepository.insertItemByMap(addItem);
        log.info("{}", i);

        MenuItem menuItem = menuRepository.queryForItem(i.getId());
        log.info("{}", menuItem);

        assertEquals(i.getName(), menuItem.getName());
        assertEquals(i.getSize(), menuItem.getSize());
        assertEquals(i.getPrice(), menuItem.getPrice());
    }

    @Test
    void testNamedParameterByBean() {
        MenuItem addItem = MenuItem.builder().name("addtest").size("big").price(BigDecimal.valueOf(1000)).build();
        MenuItem i = menuRepository.insertItemByBean(addItem);
        log.info("{}", i);

        MenuItem menuItem = menuRepository.queryForItem(i.getId());
        log.info("{}", menuItem);

        assertEquals(i.getName(), menuItem.getName());
        assertEquals(i.getSize(), menuItem.getSize());
        assertEquals(i.getPrice(), menuItem.getPrice());
    }

    @Test
    void testBatchInsert() {
        List<MenuItem> items =
                Stream.of("Go", "Python", "Devops")
                        .map(n -> MenuItem.builder().name(n).size("bigger").price(BigDecimal.valueOf(1000)).build())
                        .collect(Collectors.toList());
        int i = menuRepository.insertItems(items);
        assertEquals(3,i);
    }

}