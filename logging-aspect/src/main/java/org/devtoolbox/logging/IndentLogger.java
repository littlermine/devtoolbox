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
package org.devtoolbox.logging;

import org.devtoolbox.logging.aspect.LogIndent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
public final class IndentLogger implements Logger
	{
	private static Logger logger;
	
	/**
	 * @param name
	 */
	protected IndentLogger ( Logger aLogger )
		{
		logger = aLogger;
		}
	
	/**
	 * @param aClass
	 * @return
	 */
	public static Logger getLogger ( Class < ? > aClass )
		{
		Logger newLogger = LoggerFactory.getLogger ( aClass );
		
		return new IndentLogger ( newLogger );
		}

	@Override public String getName ( )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public boolean isTraceEnabled ( )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( String msg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( String format, Object arg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( String format, Object arg1, Object arg2 )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( String format, Object[ ] argArray )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( String msg, Throwable t )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public boolean isTraceEnabled ( Marker marker )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( Marker marker, String msg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( Marker marker, String format, Object arg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( Marker marker, String format, Object arg1, Object arg2 )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( Marker marker, String format, Object[ ] argArray )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void trace ( Marker marker, String msg, Throwable t )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public boolean isDebugEnabled ( )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void debug ( String msg )
		{
		logger.debug ( LogIndent.INSTANCE.indent ( ) + msg );
		}

	@Override public void debug ( String format, Object arg )
		{
		logger.debug ( LogIndent.INSTANCE.indent ( ) + format, arg );
		}

	@Override public void debug ( String format, Object arg1, Object arg2 )
		{
		logger.debug ( LogIndent.INSTANCE.indent ( ) + format, arg1, arg2 );
		}

	@Override public void debug ( String format, Object[ ] argArray )
		{
		logger.debug ( LogIndent.INSTANCE.indent ( ) + format, argArray );
		}

	@Override public void debug ( String msg, Throwable t )
		{
		logger.debug ( LogIndent.INSTANCE.indent ( ) + msg, t );
		}

	@Override public boolean isDebugEnabled ( Marker marker )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void debug ( Marker marker, String msg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void debug ( Marker marker, String format, Object arg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void debug ( Marker marker, String format, Object arg1, Object arg2 )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void debug ( Marker marker, String format, Object[ ] argArray )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void debug ( Marker marker, String msg, Throwable t )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public boolean isInfoEnabled ( )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( String msg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( String format, Object arg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( String format, Object arg1, Object arg2 )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( String format, Object[ ] argArray )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( String msg, Throwable t )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public boolean isInfoEnabled ( Marker marker )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( Marker marker, String msg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( Marker marker, String format, Object arg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( Marker marker, String format, Object arg1, Object arg2 )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( Marker marker, String format, Object[ ] argArray )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void info ( Marker marker, String msg, Throwable t )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public boolean isWarnEnabled ( )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void warn ( String msg )
		{
		logger.warn ( LogIndent.INSTANCE.indent ( ) + msg );
		}

	@Override public void warn ( String format, Object arg )
		{
		logger.warn ( LogIndent.INSTANCE.indent ( ) + format, arg );
		}

	@Override public void warn ( String format, Object[ ] argArray )
		{
		logger.warn ( LogIndent.INSTANCE.indent ( ) + format, argArray );
		}

	@Override public void warn ( String format, Object arg1, Object arg2 )
		{
		logger.warn ( LogIndent.INSTANCE.indent ( ) + format, arg1, arg2 );
		}

	@Override public void warn ( String msg, Throwable t )
		{
		logger.warn ( LogIndent.INSTANCE.indent ( ) + msg, t );
		}

	@Override public boolean isWarnEnabled ( Marker marker )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void warn ( Marker marker, String msg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void warn ( Marker marker, String format, Object arg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void warn ( Marker marker, String format, Object arg1, Object arg2 )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void warn ( Marker marker, String format, Object[ ] argArray )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void warn ( Marker marker, String msg, Throwable t )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public boolean isErrorEnabled ( )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void error ( String msg )
		{
		logger.error ( LogIndent.INSTANCE.indent ( ) + msg );
		}

	@Override public void error ( String format, Object arg )
		{
		logger.error ( LogIndent.INSTANCE.indent ( ) + format, arg );
		}

	@Override public void error ( String format, Object arg1, Object arg2 )
		{
		logger.error ( LogIndent.INSTANCE.indent ( ) + format, arg1, arg2 );
		}

	@Override public void error ( String format, Object[ ] argArray )
		{
		logger.error ( LogIndent.INSTANCE.indent ( ) + format, argArray );
		}

	@Override public void error ( String msg, Throwable t )
		{
		logger.error ( LogIndent.INSTANCE.indent ( ) + msg, t );
		}

	@Override public boolean isErrorEnabled ( Marker marker )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void error ( Marker marker, String msg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void error ( Marker marker, String format, Object arg )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void error ( Marker marker, String format, Object arg1, Object arg2 )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void error ( Marker marker, String format, Object[ ] argArray )
		{
		throw new UnsupportedOperationException ( );
		}

	@Override public void error ( Marker marker, String msg, Throwable t )
		{
		throw new UnsupportedOperationException ( );
		}
	}
