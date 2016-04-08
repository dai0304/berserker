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

import jp.classmethod.example.berserker.model.User;
import jp.classmethod.example.berserker.model.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
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
	UserRepository userRepos;
	
	
	@Transactional
	public void execute() {
		// 成功するDB書き込み操作
		userRepos.save(new User("torazuka", "$2a$10$fx33wHST4ecwp53MB5QvROQtIYwkdCU2O3XJK6LuCmm415dRncluC"));
		// からの失敗
		throw new RuntimeException();
	}
}
