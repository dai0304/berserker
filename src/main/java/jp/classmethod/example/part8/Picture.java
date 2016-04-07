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
package jp.classmethod.example.part8;

import java.io.Serializable;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;

@ToString
@EqualsAndHashCode(of = "pictureId")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "pictures")
@SuppressWarnings("serial")
public class Picture implements Serializable {
	
	@Getter
	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY)
	@Column(name = "picture_id")
	private long pictureId;
	
	@Getter
	@Setter
	@NonNull
	@Column(name = "location")
	private String location;
	
	@Getter
	@Setter
	@Column(name = "event_id")
	private long eventId;
	
	
	public Picture(String location, long eventId) {
		this.location = location;
		this.eventId = eventId;
	}
}
