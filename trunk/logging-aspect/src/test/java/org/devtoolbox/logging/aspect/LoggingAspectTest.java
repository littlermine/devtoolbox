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
package org.devtoolbox.logging.aspect;

import java.lang.reflect.Method;

import org.devtoolbox.logging.aspect.annotation.Loggable;
import org.devtoolbox.logging.constant.Level;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Unit test for {@link LoggingAspect} class;
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
@Listeners ( { mockit.integration.testng.Initializer.class } )
public class LoggingAspectTest
	{	
	@Loggable public void loggableMethodLower ( ) { }
	public @Test static void test_resolveLoggingLevelValue_False ( ) throws SecurityException, NoSuchMethodException
		{
		//PREPARE	
		Method annotatedMethod = LoggingAspectTest.class.getMethod ( "loggableMethodLower" );
		Loggable testLoggableAnnotation = annotatedMethod.getAnnotation ( Loggable.class );
		
		//EXECUTE
		final boolean result = LoggingAspect.logging ( testLoggableAnnotation );
		
		//VERIFY
		Assert.assertFalse ( result );
		}
	
	@Loggable ( level = Level.ERROR ) public void loggableMethodHigher ( ) { }
	public @Test static void test_resolveLoggingLevelValue_TrueHigher ( ) throws SecurityException, NoSuchMethodException
		{
		//PREPARE	
		Method annotatedMethod = LoggingAspectTest.class.getMethod ( "loggableMethodHigher" );
		Loggable testLoggableAnnotation = annotatedMethod.getAnnotation ( Loggable.class );
		
		//EXECUTE
		final boolean result = LoggingAspect.logging ( testLoggableAnnotation );
		
		//VERIFY
		Assert.assertTrue ( result );
		}
	
	@Loggable ( level = Level.INFO ) public void loggableMethodEqual ( ) { }
	public @Test static void test_resolveLoggingLevelValue_TrueEqual ( ) throws SecurityException, NoSuchMethodException
		{
		//PREPARE	
		Method annotatedMethod = LoggingAspectTest.class.getMethod ( "loggableMethodEqual" );
		Loggable testLoggableAnnotation = annotatedMethod.getAnnotation ( Loggable.class );
		
		//EXECUTE
		final boolean result = LoggingAspect.logging ( testLoggableAnnotation );
		
		//VERIFY
		Assert.assertTrue ( result );
		}
	}
