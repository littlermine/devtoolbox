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

package org.devtoolbox.property;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


/**
 * Unit test for {@link PropertyLoader} class.
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
@Listeners ( { mockit.integration.testng.Initializer.class } )
public class PropertyLoaderTest
	{
	private @Mocked ( methods = "close" ) BufferedInputStream inputStreamMock;
	
	public @Test void test_load_closeInputStream ( ) throws IOException
		{
		//PREPARE
		final String testPropertyFileName = "testFirst.properties";
		
		//EXECUTE
		new PropertyLoader.Builder ( ).load ( testPropertyFileName ).build ( );
		
		//VERIFY
		new Verifications ( )
			{{
			inputStreamMock.close ( ); times = 1;
			}};
		}
	
	public @Test void test_load_throwIOException ( )
		{
		//PREPARE
		final String testPropertyFileName = "testFirst.properties";
		new MockUp < Properties > ( )
			{
			@SuppressWarnings ( "unused" )
			@Mock public void load ( InputStream anInputStream ) throws IOException 
				{
				Assert.assertNotNull ( anInputStream );
				throw new IOException ( );
				}
			};
			
		//EXECUTE
		final Properties result = new PropertyLoader.Builder ( ).load ( testPropertyFileName ).build ( );
		
		//VERIFY
		Assert.assertNull ( result );
		}
	
	@Test ( expectedExceptions = IllegalArgumentException.class )
	public void test_load_propertyFileNameIsEmpty ( )
		{
		//PREPARE
		final String testPropertyFileName = "";
		
		//EXECUTE
		new PropertyLoader.Builder ( ).load ( testPropertyFileName ).build ( );
		}
	
	@Test ( expectedExceptions = IllegalArgumentException.class )
	public void test_load_propertyFileNameIsNull ( )
		{
		//PREPARE
		final String testPropertyFileName = null;
		
		//EXECUTE
		new PropertyLoader.Builder ( ).load ( testPropertyFileName ).build ( );
		}
	
	public @Test void test_load_openPropertyFileWithoutFileExtension ( )
		{
		//PREPARE
		final String testPropertyFileName = "testFirst";
		
		//EXECUTE
		final Properties result = new PropertyLoader.Builder ( ).load ( testPropertyFileName ).build ( );
		
		//VERIFY
		Assert.assertNotNull ( result );
		Assert.assertEquals ( result.getProperty ( "test.first.a" ), "1A" );
		Assert.assertEquals ( result.getProperty ( "test.first.b" ), "1B" );
		}
	
	public @Test void test_load_openTwoPropertyFiles ( )
		{
		//PREPARE
		final String testPropertyFileNameFirst = "testFirst.properties";
		final String testPropertyFileNameSecond = "testSecond.properties";
		
		//EXECUTE
		final Properties result = new PropertyLoader.Builder ( )
									.load ( testPropertyFileNameFirst )
									.load ( testPropertyFileNameSecond ).build ( );
		
		//VERIFY
		Assert.assertNotNull ( result );
		Assert.assertEquals ( result.getProperty ( "test.first.a" ), "1A" );
		Assert.assertEquals ( result.getProperty ( "test.first.b" ), "1B" );
		Assert.assertEquals ( result.getProperty ( "test.second.a" ), "2A" );
		Assert.assertEquals ( result.getProperty ( "test.second.b" ), "2B" );
		}
	
	public @Test void test_load_openOnePropertyFile ( )
		{
		//PREPARE
		final String testPropertyFileName = "testFirst.properties";
		
		//EXECUTE
		final Properties result = new PropertyLoader.Builder ( ).load ( testPropertyFileName ).build ( );
		
		//VERIFY
		Assert.assertNotNull ( result );
		Assert.assertEquals ( result.getProperty ( "test.first.a" ), "1A" );
		Assert.assertEquals ( result.getProperty ( "test.first.b" ), "1B" );
		}
	}
