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
package jp.classmethod.example.part1.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQSClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan("jp.classmethod.example.part1")
public class AppConfig {
	
// ComponentScanとAutowiredによって、この記述は不要になる
//  @Bean
//  public SpringQueueWorker worker() {
//    SpringQueueWorker worker = new SpringQueueWorker();
//    worker.setSqs(amazonSQSClient());
//    worker.setMailSender(javaMailSenderImpl());
//    worker.setMailTemplate(templateMessage());
//    return worker;
//  }
	
	@Bean
	public AmazonSQSClient amazonSQSClient() {
		return new AmazonSQSClient(credentialsProvider());
	}
	
	@Bean
	public ProfileCredentialsProvider credentialsProvider() {
		return new ProfileCredentialsProvider("default");
	}
	
	@Bean
	public MailSender javaMailSenderImpl() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.example.com");
		javaMailSenderImpl.setPort(25);
		javaMailSenderImpl.setUsername("username");
		javaMailSenderImpl.setPassword("password");
		return javaMailSenderImpl;
	}
	
	@Bean
	public SimpleMailMessage templateMessage() {
		SimpleMailMessage templateMessage = new SimpleMailMessage();
		templateMessage.setSubject("QueueWorker sample");
		templateMessage.setFrom("daisuke@example.com");
		return templateMessage;
	}
}
