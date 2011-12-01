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
package org.devtoolbox.errorhandling.impl;

import java.io.File;
import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.commons.io.FileUtils;
import org.devtoolbox.errorhandling.exception.FileMoverException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link OnErrorFileMover} class
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OnErrorFileMoverTest {

	private OnErrorFileMover unitUnderTest;
	
	private @Mock Exchange exchangeMock;
	
	private String fileName = "OnErrorFileMoverTest.txt";
	private String rootFolderPath;
	private String processingFolderPath = "processing";
	private String errorFolderPath = "error";
	
	private final String fileSeparator = System.getProperty("file.separator");
	
	public @Before void setUp() throws IOException {
		
		unitUnderTest = new OnErrorFileMover();

		java.security.ProtectionDomain pd = OnErrorFileMoverTest.class.getProtectionDomain();
		java.security.CodeSource cs = pd.getCodeSource();
		java.net.URL url = cs.getLocation();
		java.io.File f = new File(url.getFile());
		rootFolderPath = f.getAbsolutePath();
		
		FileUtils.deleteDirectory(new File(rootFolderPath + fileSeparator + processingFolderPath));
		FileUtils.deleteDirectory(new File(rootFolderPath + fileSeparator + errorFolderPath));
		FileUtils.copyFile(new java.io.File("src/test/resources/org/devtoolbox/errorhandling/" + fileName),
		   		   		   new java.io.File(rootFolderPath + fileSeparator + processingFolderPath + fileSeparator + fileName));
	}
	
	public @Test void test_move_success() throws IOException {
		
		//PREPARE
		final String testOriginalFileName = fileName;
		final String testRootFolderPath = rootFolderPath;
		final String testProcessingFolderPath = processingFolderPath;
		final String testErrorFolderPath = errorFolderPath;
		Mockito.when(exchangeMock.getProperty("originalFileName", String.class)).thenReturn(testOriginalFileName);
		Mockito.when(exchangeMock.getProperty("rootFolderPath", String.class)).thenReturn(testRootFolderPath);
		Mockito.when(exchangeMock.getProperty("processingFolderPath", String.class)).thenReturn(testProcessingFolderPath);
		Mockito.when(exchangeMock.getProperty("errorFolderPath", String.class)).thenReturn(testErrorFolderPath);
		
		//EXECUTE
		unitUnderTest.move(exchangeMock);
		
		//VALIDATE
		Assert.assertFalse(new File(rootFolderPath + fileSeparator + processingFolderPath + fileSeparator + fileName).exists());
		Assert.assertTrue(new File(rootFolderPath + fileSeparator + errorFolderPath + fileSeparator + fileName).exists());
	}
	
	@Test(expected = FileMoverException.class)
	public void test_move_errorFolderPatMissing() throws IOException {
		
		//PREPARE
		final String testOriginalFileName = "testOriginalFileName";
		final String testRootFolderPath = "testRootFolderPath";
		final String testProcessingFolderPath = "testProcessingFolderPath";
		final String testErrorFolderPath = null;
		Mockito.when(exchangeMock.getProperty("originalFileName", String.class)).thenReturn(testOriginalFileName);
		Mockito.when(exchangeMock.getProperty("rootFolderPath", String.class)).thenReturn(testRootFolderPath);
		Mockito.when(exchangeMock.getProperty("processingFolderPath", String.class)).thenReturn(testProcessingFolderPath);
		Mockito.when(exchangeMock.getProperty("errorFolderPath", String.class)).thenReturn(testErrorFolderPath);
		
		//EXECUTE
		unitUnderTest.move(exchangeMock);
	}
	
	@Test(expected = FileMoverException.class)
	public void test_move_processingFolderPathMissing() throws IOException {
		
		//PREPARE
		final String testOriginalFileName = "testOriginalFileName";
		final String testRootFolderPath = "testRootFolderPath";
		final String testProcessingFolderPath = null;
		final String testErrorFolderPath = "testErrorFolderPath";
		Mockito.when(exchangeMock.getProperty("originalFileName", String.class)).thenReturn(testOriginalFileName);
		Mockito.when(exchangeMock.getProperty("rootFolderPath", String.class)).thenReturn(testRootFolderPath);
		Mockito.when(exchangeMock.getProperty("processingFolderPath", String.class)).thenReturn(testProcessingFolderPath);
		Mockito.when(exchangeMock.getProperty("errorFolderPath", String.class)).thenReturn(testErrorFolderPath);
		
		//EXECUTE
		unitUnderTest.move(exchangeMock);
	}
	
	@Test(expected = FileMoverException.class)
	public void test_move_rootFolderPathMissing() throws IOException {
		
		//PREPARE
		final String testOriginalFileName = "testOriginalFileName";
		final String testRootFolderPath = null;
		final String testProcessingFolderPath = "testProcessingFolderPath";
		final String testErrorFolderPath = "testErrorFolderPath";
		Mockito.when(exchangeMock.getProperty("originalFileName", String.class)).thenReturn(testOriginalFileName);
		Mockito.when(exchangeMock.getProperty("rootFolderPath", String.class)).thenReturn(testRootFolderPath);
		Mockito.when(exchangeMock.getProperty("processingFolderPath", String.class)).thenReturn(testProcessingFolderPath);
		Mockito.when(exchangeMock.getProperty("errorFolderPath", String.class)).thenReturn(testErrorFolderPath);
		
		//EXECUTE
		unitUnderTest.move(exchangeMock);
	}
	
	@Test(expected = FileMoverException.class)
	public void test_move_originalFileNameMissing() throws IOException {
		
		//PREPARE
		final String testOriginalFileName = null;
		final String testRootFolderPath = "testRootFolderPath";
		final String testProcessingFolderPath = "testProcessingFolderPath";
		final String testErrorFolderPath = "testErrorFolderPath";
		Mockito.when(exchangeMock.getProperty("originalFileName", String.class)).thenReturn(testOriginalFileName);
		Mockito.when(exchangeMock.getProperty("rootFolderPath", String.class)).thenReturn(testRootFolderPath);
		Mockito.when(exchangeMock.getProperty("processingFolderPath", String.class)).thenReturn(testProcessingFolderPath);
		Mockito.when(exchangeMock.getProperty("errorFolderPath", String.class)).thenReturn(testErrorFolderPath);
		
		//EXECUTE
		unitUnderTest.move(exchangeMock);
	}
}
