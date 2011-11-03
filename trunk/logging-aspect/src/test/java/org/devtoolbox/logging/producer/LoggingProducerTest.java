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
package org.devtoolbox.logging.producer;

import java.util.Properties;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Injectable;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;

/**
 * Unit test for {@link LoggingProducer} class.
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 * 
 */
@Listeners ( { mockit.integration.testng.Initializer.class } )
public class LoggingProducerTest
	{
	private LoggingProducer unitUnderTest;
	
	private LoggingProducer loggingProducerInject;
	
	private @Injectable Properties loggingPropertiesMock;
	
	private Weld weld;
	
	public @BeforeClass void setUp ( ) 
		{
		unitUnderTest = new LoggingProducer ( );
		
		weld = new Weld ( );
		WeldContainer container = weld.initialize ( );
		loggingProducerInject = container.instance ( ).select ( LoggingProducer.class ).get ( );

		Deencapsulation.setField ( unitUnderTest, loggingPropertiesMock );
		}
	
	public @AfterClass void tearDown ( ) 
		{
		weld.shutdown ( );
		}
	
	public @Test void test_getLoggingLevelProperty_propertyFound ( )
		{
		//PREPARE
		final String testProperty = "WARN";
		new Expectations ( )
			{{
			loggingPropertiesMock.getProperty ( "logging.level" ); result = testProperty;
			}};
		
		//EXECUTE
		final Level result = unitUnderTest.getLoggingLevelProperty ( null );
		
		//VERIFY
		Assert.assertEquals ( result, Level.WARN );
		}
	
	public @Test void test_getLoggingLevelProperty_propertyNotFound ( )
		{
		//PREPARE
		final String testProperty = null;
		new Expectations ( )
			{{
			loggingPropertiesMock.getProperty ( "logging.level" ); result = testProperty;
			}};
		
		//EXECUTE
		final Level result = unitUnderTest.getLoggingLevelProperty ( null );
		
		//VERIFY
		Assert.assertEquals ( result, Level.DEBUG );
		}
	
	public @Test void test_getLoggingIndentProperty_propertyFound ( )
		{
		//PREPARE
		final String testProperty = "true";
		new Expectations ( )
			{{
			loggingPropertiesMock.getProperty ( "logging.indent" ); result = testProperty;
			}};
		
		//EXECUTE
		final boolean result = unitUnderTest.getLoggingIndentProperty ( null );
		
		//VERIFY
		Assert.assertEquals ( result, true );
		}
	
	public @Test void test_getLoggingIndentProperty_propertyNotFound ( )
		{
		//PREPARE
		final String testProperty = null;
		new Expectations ( )
			{{
			loggingPropertiesMock.getProperty ( "logging.indent" ); result = testProperty;
			}};
		
		//EXECUTE
		final boolean result = unitUnderTest.getLoggingIndentProperty ( null );
		
		//VERIFY
		Assert.assertEquals ( result, false );
		}
	
	public @Test void test_getLoggingLevelProperty_inject ( )
		{
		//EXECUTE
		final Level result = loggingProducerInject.getLoggingLevelProperty ( null );
		
		//VERIFY
		Assert.assertNotNull ( result );
		Assert.assertEquals ( result, Level.INFO );
		}
	
	public @Test void test_getLoggingIndentProperty_inject ( )
		{
		//EXECUTE
		final boolean result = loggingProducerInject.getLoggingIndentProperty ( null );
		
		//VERIFY
		Assert.assertEquals ( result, true );
		}
	}
