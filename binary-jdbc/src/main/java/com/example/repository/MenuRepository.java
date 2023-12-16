package com.example.repository;

import com.example.model.MenuItem;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public class MenuRepository {

    public static final String INSERT_SQL = "insert into t_menu (name, size, price, create_time, update_time) values " +
            "(?, ?, ?, now(), now())";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public MenuRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public Long countMenuItems() {
        return jdbcTemplate.queryForObject("select count(*) from t_menu", Long.class);
    }

    public MenuItem queryForItem(Long id) {
        return jdbcTemplate.queryForObject("select * from t_menu where id = ?", menuItemRowMapper(), id);
    }

    private RowMapper<MenuItem> menuItemRowMapper() {
        return (resultSet, rowNum) -> {
            return MenuItem.builder().id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .size(resultSet.getString("size"))
                    .price(resultSet.getBigDecimal("price"))
                    .createTime(resultSet.getDate("create_time"))
                    .updateTime(resultSet.getDate("update_time"))
                    .build();
        };
    }

    /**
     * jdbcTemplate 执行 update 方法
     *
     * @param item
     * @return
     */
    public int insertItem(MenuItem item) {
        return jdbcTemplate.update(INSERT_SQL, item.getName(), item.getSize(), item.getPrice());
    }

    /**
     * 插入记录后能取得生成的 ID，使用 KeyHolder 类来持有生成的键
     *
     * @param menuItem
     * @return
     */
    public int insertItemAndFillId(MenuItem menuItem) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affected = jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_SQL,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, menuItem.getName());
            preparedStatement.setString(2, menuItem.getSize());
            preparedStatement.setBigDecimal(3, menuItem.getPrice());
            return preparedStatement;
        }, keyHolder);

        if (affected == 1) {
            if (keyHolder.getKey() != null) {
                menuItem.setId(keyHolder.getKey().longValue());
            }
        }

        return affected;
    }

    /**
     * named jdbcTemplate 执行 update 方法，MapSqlParameterSource
     *
     * @param item
     * @return
     */
    public MenuItem insertItemByMap(MenuItem item) {
        String sql = "insert into t_menu (name, size, price, create_time, update_time) values (:name, :size, :price, " +
                "now(), now())";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", item.getName());
        parameterSource.addValue("size", item.getSize());
        parameterSource.addValue("price", item.getPrice());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = namedJdbcTemplate.update(sql, parameterSource, keyHolder);
        if (keyHolder.getKey()!=null) {
            item.setId(keyHolder.getKey().longValue());
        }
        return item;
    }

    /**
     * named jdbcTemplate 执行 update 方法，BeanPropertySqlParameterSource
     *
     * @param item
     * @return
     */
    public MenuItem insertItemByBean(MenuItem item) {
        String sql = "insert into t_menu (name, size, price, create_time, update_time) values (:name, :size, :price, " +
                "now(), now())";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(item);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = namedJdbcTemplate.update(sql, parameterSource, keyHolder);
        if (keyHolder.getKey()!=null) {
            item.setId(keyHolder.getKey().longValue());
        }
        return item;
    }

    public int insertItems(List<MenuItem> items){
        String sql = "insert into t_menu (name, size, price, create_time, update_time) values " +
                "(?, ?, ?, now(), now())";
        int[] ints = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                MenuItem menuItem = items.get(i);
                ps.setString(1, menuItem.getName());
                ps.setString(2, menuItem.getSize());
                ps.setBigDecimal(3, menuItem.getPrice());
            }

            @Override
            public int getBatchSize() {
                return items.size();
            }
        });

        return Arrays.stream(ints).sum();
    }

    public int insertItemsByNamed(List<MenuItem> items){
        String sql = "insert into t_menu (name, size, price, create_time, update_time) values " +
                "(?, ?, ?, now(), now())";

        int[] count = namedJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(items));
        return Arrays.stream(count).sum();
    }
}
