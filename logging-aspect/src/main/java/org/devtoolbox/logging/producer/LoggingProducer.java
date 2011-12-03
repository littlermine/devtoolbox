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

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.commons.lang.StringUtils;
import org.devtoolbox.logging.producer.qualifier.LoggingIndentProperty;
import org.devtoolbox.logging.producer.qualifier.LoggingLevelProperty;
import org.devtoolbox.property.PropertyLoader;

import ch.qos.logback.classic.Level;

/**
 * CDI {@link javax.enterprise.inject.spi.Producer} of logging propertied.
 * 
 * Logging properties are read from the {@code logging.properties} property file. 
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 * 
 */
final class LoggingProducer
	{
	private Properties loggingProperties;
	
	/**
	 * Load logging properties from properties file on initialization of {@link LoggingProducer} 
	 */
	public @PostConstruct void initialization ( ) 
		{
		loggingProperties = new PropertyLoader.Builder ( ).load ( "logging.properties" ).build ( );
		}
	
	/**
	 * Return value of the indent logging property (i.e. {@code logging.indent}) specified in the 
	 * {@code logging.properties} property file.
	 * 
	 * If such property was not loaded in {@link Properties} the default value of {@link false}
	 * is returned.
	 * 
	 * @param p
	 * @return
	 * 		Indent property or {@code null} if such property was not found
	 */
	public @Produces @LoggingIndentProperty boolean getLoggingIndentProperty ( InjectionPoint p )
		{
		boolean result = false;
		
		String indentProperty = loggingProperties.getProperty ( "logging.indent" );
		if ( StringUtils.isNotEmpty ( indentProperty ) )
			{
			result = Boolean.valueOf ( indentProperty );
			}
		
		return result;
		}
	
	/**
	 * Return value of the indent logging property ({i.e. @code logging.level}) specified in the 
	 * {@code logging.properties} property file.
	 * 
	 * If such property was not loaded in {@link Properties} the default value of {@link Level.DEBUG}
	 * is returned.
	 * 
	 * @param p
	 * @return
	 * 		Logging level property or {@code null} if such property was not found
	 */
	public @Produces @LoggingLevelProperty Level getLoggingLevelProperty ( InjectionPoint p )
		{
		Level result = Level.DEBUG;
		
		String indentProperty = loggingProperties.getProperty ( "logging.level.min" );
		if ( StringUtils.isNotEmpty ( indentProperty ) )
			{
			result = Level.valueOf ( indentProperty );
			}
		
		return result;
		}
	}
