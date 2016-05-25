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
package jp.classmethod.example.berserker.web;

import java.util.Date;

import jp.classmethod.example.berserker.model.User;
import jp.classmethod.example.berserker.model.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC Controller.
 * 
 * @since #version#
 * @author daisuke
 */
@Slf4j
@Controller
public class RootController {
	
	@Autowired
	UserRepository userRepos;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional
	public String index(Model model) {
		log.debug("index");
		Iterable<User> users = userRepos.findAll();
		model.addAttribute("now", new Date().toString());
		model.addAttribute("users", users);
		return "index";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@Transactional
	@ResponseBody
	public ResponseEntity<?> users() {
		log.debug("users");
		Iterable<User> users = userRepos.findAll();
		return ResponseEntity.ok(users);
	}
}
