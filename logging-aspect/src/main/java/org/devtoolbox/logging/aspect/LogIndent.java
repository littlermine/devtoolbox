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

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.devtoolbox.property.PropertyLoader;

/**
 * Singleton holding the indent value of the method stack
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 * 
 */
public enum LogIndent
	{
	INSTANCE;

	private int indent = 0;
	
	/**
	 * This property indicates whether logging should be done with the indent of the logged methods.
	 */
	private boolean isIndent;
	
	/**
	 * In case indent is enabled, the method will be prepended with the symbol assigned to that variable.
	 * Default value is dash (-) symbol;
	 */
	private String indentSymbol = "-";
	
	private LogIndent ( )
		{
		Properties loggingProperties = new PropertyLoader.Builder ( ).load ( "logging.properties" ).build ( );
		if ( null != loggingProperties )
			{
			isIndent = resolveIndentValue ( loggingProperties );
			indentSymbol = resolveIndentSymbolValue ( loggingProperties );
			}
		}
	
	private static boolean resolveIndentValue ( Properties aProperty )
		{
		boolean result = false;
		
		String indentPropertyValue = aProperty.getProperty ( INDENT_PROPERTY_NAME );
		if ( StringUtils.isNotEmpty ( indentPropertyValue ) )
			{
			result = Boolean.valueOf ( indentPropertyValue );
			}
		
		return result;
		}
	
	private static String resolveIndentSymbolValue ( Properties aProperty )
		{
		String result = "-";
		
		String indentSymbolPropertyValue = aProperty.getProperty ( INDENT_SYMBOL_PROPERTY_NAME );
		if ( StringUtils.isNotEmpty ( indentSymbolPropertyValue ) )
			{
			result = indentSymbolPropertyValue;
			}
		
		return result;
		}
	
	/**
	 * 
	 */
	void increment ( )
		{
		indent++ ;
		}

	/**
	 * 
	 */
	void decrement ( )
		{
		indent-- ;
		}

	/**
	 * @return
	 */
	public String indent ( )
		{
		StringBuffer stringBuffer = new StringBuffer ( );

		if ( isIndent )
			{
			for ( int i = 0; i < indent; i++ )
				{
				stringBuffer.append ( indentSymbol );
				}
			stringBuffer.append ( " " );
			}

		return stringBuffer.toString ( );
		}
	
	private static final String INDENT_PROPERTY_NAME = "logging.indent";
	private static final String INDENT_SYMBOL_PROPERTY_NAME = "logging.indent.symbol";
	}
