/*
 * Copyright 2013-2016 Classmethod, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.classmethod.example.berserker;

import java.sql.ResultSet;
import java.sql.SQLException;

import jp.classmethod.example.berserker.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ComponentScan
public class DataAccessSample {
	
	private static Logger logger = LoggerFactory.getLogger(DataAccessSample.class);
	
	
	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DataAccessSample.class)) {
			DataAccessSample das = context.getBean(DataAccessSample.class);
			das.execute();
		}
	}
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Transactional
	public void execute() {
		Long allUsersCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Long.class);
		logger.info("allUsersCount = {}", allUsersCount);
		
		String password = jdbcTemplate.queryForObject("SELECT password FROM users WHERE username = ?", new Object[] {
				"miyamoto"
		}, String.class);
		logger.info("password = {}", password);
		
		User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", new Object[] {
				"miyamoto"
		}, new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		logger.info("user = {}", user);
	}
}
