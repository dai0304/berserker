/*
 * Copyright 2013-2015 Classmethod, Inc..
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
package jp.classmethod.example.part1;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SpringQueueWorker {
	
	private static final String QUEUE_URL = "...";
	
	@Setter
	private AmazonSQS sqs;
	
	@Setter
	private MailSender mailSender;
	
	@Setter
	private SimpleMailMessage mailTemplate;
	
	
	public void execute() {
		ReceiveMessageResult result = sqs.receiveMessage(new ReceiveMessageRequest(QUEUE_URL));
		List<SimpleMailMessage> mails = new ArrayList<>();
		for (Message message : result.getMessages()) {
			SimpleMailMessage mail = new SimpleMailMessage(mailTemplate);
			mail.setTo(message.getAttributes().get("to"));
			mail.setText(message.getBody());
			mails.add(mail);
			sqs.deleteMessage(new DeleteMessageRequest(QUEUE_URL, message.getReceiptHandle()));
		}
		mailSender.send(mails.toArray(new SimpleMailMessage[mails.size()]));
	}
}
