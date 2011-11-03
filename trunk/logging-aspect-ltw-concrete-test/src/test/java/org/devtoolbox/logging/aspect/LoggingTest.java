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

import org.devtoolbox.log.LoggableClass;
import org.testng.annotations.Test;

/**
 * Test logging using aspects, i.e. defined in {@code aop.xml} in {@literal <aspect>} tag
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
public class LoggingTest
	{
	public @Test void testLogAspect ( )
		{
//		Weld weld = new Weld ( );
//		WeldContainer container = weld.initialize ( );
//		LoggableClass loggableClass = container.instance ( ).select ( LoggableClass.class ).get ( );
		LoggableClass loggableClass = new LoggableClass ( );
		loggableClass.log ( );
		}
	
/*	@Test ( expectedExceptions = UnsupportedOperationException.class )
	public void testLogAspectException ( )
		{
//		Weld weld = new Weld ( );
//		WeldContainer container = weld.initialize ( );
//		LoggableClass loggableClass = container.instance ( ).select ( LoggableClass.class ).get ( );
		LoggableClass loggableClass = new LoggableClass ( );
		loggableClass.logException ( );
		}*/
	}
