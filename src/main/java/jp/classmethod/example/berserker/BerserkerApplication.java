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

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BerserkerApplication {
	
	private static Logger logger = LoggerFactory.getLogger(BerserkerApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BerserkerApplication.class);
		app.run(args);
	}
	
	
	@Autowired
	UserRepository userRepos;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<String> index() {
		logger.debug("index");
		Iterable<User> users = userRepos.findAll();
		String result = StreamSupport.stream(users.spliterator(), false)
			.map(Object::toString)
			.collect(Collectors.joining(","));
		return ResponseEntity.ok(result);
	}
}
