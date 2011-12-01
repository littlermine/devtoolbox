package org.devtoolbox.errorhandling;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

/**
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/failure-context-test.xml",
        "classpath:META-INF/spring/failure-context.xml"
})
public class RouteFailureTest extends AbstractJUnit4SpringContextTests {

	private @Value("${folder.root}") String rootFolderPath;
	private @Value("${folder.error}") String errorFolderPath;
	private @Value("${folder.processing}") String processingFolderPath;

	private String testFileName = "OnErrorFileMoverTest.txt";

	public @Before void setUp() throws IOException {

		FileUtils.deleteDirectory(new java.io.File(rootFolderPath));
		FileUtils.copyFile(new java.io.File("src/test/resources/org/devtoolbox/errorhandling/" + testFileName),
				   		   new java.io.File(rootFolderPath + "/" + testFileName));
	}

	public @Test void testRoute() throws Exception {

		Wiser wiser = new Wiser();
		wiser.start();
		Thread.sleep(10000); //wait for route to execute

        Assert.assertFalse(new File(rootFolderPath + "/" + processingFolderPath + "/" + testFileName).exists());
        Assert.assertTrue(new File(rootFolderPath + "/" + errorFolderPath + "/" + testFileName).exists());

		List<WiserMessage> wiserMessageList = wiser.getMessages();
		Assert.assertTrue(wiserMessageList.size() > 0);
		for (WiserMessage message : wiserMessageList) {

			String envelopeSender = message.getEnvelopeSender();
			Assert.assertNotNull(envelopeSender);
			Assert.assertEquals("some@some.com", envelopeSender);
			
			String envelopeReceiver = message.getEnvelopeReceiver();
			Assert.assertNotNull(envelopeReceiver);
			Assert.assertEquals("some@some.com", envelopeReceiver);

			MimeMessage mimeMessage = message.getMimeMessage();
			Assert.assertNotNull(mimeMessage);
			Object content = mimeMessage.getContent();
			Assert.assertEquals("The processing of the file " + testFileName + " has failed. The file was moved to the " + rootFolderPath + "/" + errorFolderPath + " folder.", content.toString());
		}
		wiser.stop();
	}
}
