package com.miaosha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("miaosha")
public class DatabaseService {

	@Autowired
	JdbcTemplate jdbcTemplate;

}