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
package org.devtoolbox.log;

import org.devtoolbox.logging.IndentLogger;
import org.devtoolbox.logging.aspect.annotation.Loggable;
import org.devtoolbox.logging.constant.Level;
import org.slf4j.Logger;

/**
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
public class LoggableClass
	{
	@Loggable public void log ( )
		{
		logger.debug ( "log" );
		try
			{
			logException ( "stringParam", 999 );
			}
		catch ( UnsupportedOperationException e )
			{
			logger.error ( "Caught exception." );
			}
		}
	
	@Loggable ( level = Level.INFO ) public void logException ( String a, int b)
		{
		logger.debug ( "logException" );
		throw new UnsupportedOperationException ( "Bad mood" );
		}
	
	private static Logger logger = IndentLogger.getLogger ( LoggableClass.class );
	}
