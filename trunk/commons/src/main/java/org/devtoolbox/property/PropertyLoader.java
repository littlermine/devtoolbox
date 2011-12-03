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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Convenience class to load multiple {@link Properties} using a builder.
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 * 
 */
public abstract class PropertyLoader
	{
	
	/**
	 * {@link Properties} builder.
	 * 
	 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
	 * 
	 */
	public static class Builder
		{
		Properties properties = new Properties ( );
		
		/**
		 * The method can be called several times for every {@link Properties}
		 * file you wish to load.
		 * 
		 * If error occurred on loading of a property file the method silently returns.
		 * 
		 * @param aPropertyFileName 
		 * 		Name of a properties file available in the classpath. Name can be
		 * 		specified either with or without '.properties' extension.
		 * 
		 * @return
		 * 		Instance of {@link Properties} file containing properties of all
		 * 		successfully loaded properties specified on invocation.
		 */
		public Builder load ( String aPropertyFileName )
			{
			final ClassLoader loader = ClassLoader.getSystemClassLoader ( );
			
			Validate.notEmpty ( aPropertyFileName, "Specified property file name is either null or empty." );

			InputStream in = null;
			try
				{
				if ( !aPropertyFileName.endsWith ( PROPERTIES_FILE_EXTENSION ) )
					{
					aPropertyFileName = aPropertyFileName.concat ( PROPERTIES_FILE_EXTENSION );
					}

				// Returns null on lookup failures:
				in = loader.getResourceAsStream ( aPropertyFileName );
				if ( in != null )
					{
					properties.load ( in ); // Can throw IOException
					}
				}
			catch ( IOException e )
				{
				logger.debug ( "Was not able to load properties from the \"{}\" property file. Reason: {}", aPropertyFileName, e.getMessage ( ) );
				
				properties = null;
				}
			finally
				{
				if ( in != null ) // if IOException, InputStream will be null
					{
					try
						{
						in.close ( );
						}
					catch ( Throwable t )
						{
						logger.debug ( "Was not able to close \"{}\" file InputStream. Reason: {}", aPropertyFileName, t.getMessage ( ) );
						}
					}
				}
			
			return this;
			}
		
		/**
		 * @return
		 * 		{@link Properties}  object containing properties from all successful loaded property files.
		 */
		public Properties build ( )
			{
			return properties;
			}
		}

	private static final String PROPERTIES_FILE_EXTENSION = ".properties";
	private static Logger logger = LoggerFactory.getLogger ( PropertyLoader.class );
	}
