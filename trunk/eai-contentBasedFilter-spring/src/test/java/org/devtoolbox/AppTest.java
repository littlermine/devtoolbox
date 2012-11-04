/*
 * Copyright 2011 Janis Kazakovs <janis.kazakovs@opatopa.com>.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.devtoolbox;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;
import org.w3c.dom.Document;

/**
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 * 
 */
public class AppTest {

	private AbstractApplicationContext applicationContext;
	
	@Before public void setUp() {
		
		applicationContext = new ClassPathXmlApplicationContext("/META-INF/spring/cbf-context.xml", AppTest.class);		
	}
	
	@After public void tearDown() {
		
		applicationContext.close();
	}
	
	@Test public void test_infant() throws Exception {
		
		MessageChannel messageChannel = (MessageChannel) applicationContext.getBean("passengerChannel");
		
		//infant
		messageChannel.send(createXmlMessageFromResource("message/passenger-infant.xml"));
		
		//child
		messageChannel.send(createXmlMessageFromResource("message/passenger-child.xml"));
		
		//adult
		messageChannel.send(createXmlMessageFromResource("message/passenger-adult.xml"));
		
		//error
		messageChannel.send(createXmlMessageFromResource("message/passenger-error.xml"));
	}
	
	private static GenericMessage<Document> createXmlMessageFromResource(String path) throws Exception {
		Resource orderRes = new ClassPathResource(path);

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		Document orderDoc = builder.parse(orderRes.getInputStream());
		return new GenericMessage<Document>(orderDoc);
	}
}
