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

import java.util.List;

import jp.classmethod.example.berserker.model.User;
import jp.classmethod.example.berserker.model.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class BerserkerApplication implements CommandLineRunner {
	
	private static Logger logger = LoggerFactory.getLogger(BerserkerApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BerserkerApplication.class);
		app.setWebEnvironment(false);
		app.run(args);
	}
	
	
	@Autowired
	UserRepository userRepos;
	
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		boolean flag = userRepos.exists("yokota");
		
		// create
		logger.info("Create user");
		if (flag) {
			userRepos.save(new User("watanabe", "$2a$10$MHPqWJ61alnBlUbvjEGK/uWRvwtYzolWCuFXW8YMJkT54HUB0H9iq"));
		} else {
			userRepos.save(new User("yokota", "$2a$10$nkvNPCb3Y1z/GSearD7s7OBdS9BoLBss3D4enbFQIgNJDvr0Xincm"));
		}
		
		// read
		logger.info("List user");
		Iterable<User> all = userRepos.findAll();
		for (User user : all) {
			logger.info("  {}", user);
		}
		
		logger.info("List user filtered by length");
		Page<User> p = userRepos.findByUsernameMaxLength(6, new PageRequest(0, 2));
		List<User> users = p.getContent();
		assert users.size() <= 2;
		for (User user : users) {
			logger.info("  {}", user);
		}
		
		logger.info("Get user");
		User miyamoto = userRepos.findOne("miyamoto");
		logger.info("  {}", miyamoto);
		
		// update
		logger.info("Update user");
		miyamoto.setPassword("$2a$10$vxq4n5VB4bsgUlBK9DXV9edhX911Qz/5iYqjLi/6qZPp8Xl7ZACKC");
		userRepos.save(miyamoto);
		
		// delete
		logger.info("Delete user");
		if (flag) {
			userRepos.delete("yokota");
		} else {
			userRepos.delete("watanabe");
		}
	}
}
