package com.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MenuItem {
	private Long id;
	private String name;
	private String size;
	private BigDecimal price;
	private Date createTime;
	private Date updateTime;
}
