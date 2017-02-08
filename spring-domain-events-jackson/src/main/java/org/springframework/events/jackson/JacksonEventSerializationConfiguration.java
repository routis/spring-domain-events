/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.events.jackson;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.events.config.DefaultingObjectFactory;
import org.springframework.events.config.EventSerializationConfigurationExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Oliver Gierke
 */
@Configuration
@RequiredArgsConstructor
public class JacksonEventSerializationConfiguration implements EventSerializationConfigurationExtension {

	private final ObjectFactory<ObjectMapper> mapper;

	@Bean
	public JacksonEventSerializer jacksonEventSerializer() {
		return new JacksonEventSerializer(new DefaultingObjectFactory<ObjectMapper>(mapper, () -> defaultObjectMapper()));
	}

	private static ObjectMapper defaultObjectMapper() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		return mapper;
	}
}
