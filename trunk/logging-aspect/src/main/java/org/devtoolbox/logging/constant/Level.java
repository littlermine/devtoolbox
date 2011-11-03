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
package org.devtoolbox.logging.constant;


/**
 * Specifies logging levels
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 * 
 */
public enum Level
	{
	ERROR ( 40000 , "ERROR" ), WARN ( 30000 , "WARN" ), INFO ( 20000 , "INFO" ), DEBUG ( 10000 , "DEBUG" ), TRACE ( 5000 , "TRACE" );

	public static final int OFF_INT = Integer.MAX_VALUE;
	public static final int ERROR_INT = 40000;
	public static final int WARN_INT = 30000;
	public static final int INFO_INT = 20000;
	public static final int DEBUG_INT = 10000;
	public static final int TRACE_INT = 5000;
	public static final int ALL_INT = Integer.MIN_VALUE;

	public static final Integer OFF_INTEGER = new Integer ( OFF_INT );
	public static final Integer ERROR_INTEGER = new Integer ( ERROR_INT );
	public static final Integer WARN_INTEGER = new Integer ( WARN_INT );
	public static final Integer INFO_INTEGER = new Integer ( INFO_INT );
	public static final Integer DEBUG_INTEGER = new Integer ( DEBUG_INT );
	public static final Integer TRACE_INTEGER = new Integer ( TRACE_INT );
	public static final Integer ALL_INTEGER = new Integer ( ALL_INT );

	public final int levelInt;
	public final String levelStr;

	private Level ( int levelInt, String levelStr )
		{
		this.levelInt = levelInt;
		this.levelStr = levelStr;
		}

	public final String toString ( )
		{
		return levelStr;
		}

	public final int toInt ( )
		{
		return levelInt;
		}

	public boolean isGreaterOrEqual ( Level r )
		{
		return levelInt >= r.levelInt;
		}

	public static Level toLevel ( String sArg )
		{
		return toLevel ( sArg, Level.DEBUG );
		}

	public static Level toLevel ( int val )
		{
		return toLevel ( val, Level.DEBUG );
		}

	public static Level toLevel ( int val, Level defaultLevel )
		{
		switch ( val )
			{
			case TRACE_INT:
				return TRACE;
			case DEBUG_INT:
				return DEBUG;
			case INFO_INT:
				return INFO;
			case WARN_INT:
				return WARN;
			case ERROR_INT:
				return ERROR;
			default:
				return defaultLevel;
			}
		}

	public static Level toLevel ( String sArg, Level defaultLevel )
		{
		if ( sArg == null )
			{
			return defaultLevel;
			}

		if ( sArg.equalsIgnoreCase ( "TRACE" ) )
			{
			return Level.TRACE;
			}
		if ( sArg.equalsIgnoreCase ( "DEBUG" ) )
			{
			return Level.DEBUG;
			}
		if ( sArg.equalsIgnoreCase ( "INFO" ) )
			{
			return Level.INFO;
			}
		if ( sArg.equalsIgnoreCase ( "WARN" ) )
			{
			return Level.WARN;
			}
		if ( sArg.equalsIgnoreCase ( "ERROR" ) )
			{
			return Level.ERROR;
			}
		
		return defaultLevel;
		}
	}
