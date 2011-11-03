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
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.devtoolbox.logging.aspect.annotation.Loggable;
import org.devtoolbox.logging.constant.Level;
import org.devtoolbox.property.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 * 
 */
public @Aspect class LoggingAspect
	{
	/**
	 * Boolean variable specifying whether method's parameters should be included in the logging.
	 */
	private static boolean isMethodParameter;
	
	/**
	 * Minimal level at which {@link LoggingAspect} advises should be weaved into
	 * the join points identified by the {@link LoggingAspect}'s pointcut. Default value 
	 * is {@code org.devtoolbox.logging.constant.Level#DEBUG)
	 */
	private static Level minLogLevel;
	
	static 
		{
		Properties loggingProperties = new PropertyLoader.Builder ( ).load ( "logging.properties" ).build ( );
		if ( null != loggingProperties )
			{
			minLogLevel = resolveLoggingLevelValue ( loggingProperties );
			isMethodParameter = resolveMethodParameterValue ( loggingProperties );
			}
		}
	
	private static boolean resolveMethodParameterValue ( Properties aProperty )
		{
		boolean result = false;
		
		String methodParameterPropertyValue = aProperty.getProperty ( METHOD_PARAMETERS_PROPERTY_NAME );
		if ( StringUtils.isNotEmpty ( methodParameterPropertyValue ) )
			{
			result = Boolean.valueOf ( methodParameterPropertyValue );
			}
		
		return result;
		}
	
	private static Level resolveLoggingLevelValue ( Properties aProperty )
		{
		Level result = Level.DEBUG;
		
		String loggingLevelPropertyValue = aProperty.getProperty ( LOGGING_LEVEL_PROPERTY_NAME );
		if ( StringUtils.isNotEmpty ( loggingLevelPropertyValue ) )
			{
			result = Level.toLevel ( loggingLevelPropertyValue.trim ( ) );
			}
		
		return result;
		}
	
	/**
	 * @param loggable
	 * @return
	 */
	@Pointcut ( "execution(* *(..)) && @annotation(loggable) && if()" )
	public static boolean logging ( Loggable loggable ) 
		{
		boolean result = false;
		Level annotationLogLevel = loggable.level ( );
		if ( annotationLogLevel.isGreaterOrEqual ( minLogLevel ) )
			{
			result = true;
			}
		
		return result;
		}
	
	private void log ( Level aLogLevel, Logger aLogger, String aMessage )
		{
		if ( aLogLevel == Level.ERROR )
			{
			aLogger.error ( aMessage );
			}
		else if ( aLogLevel == Level.WARN )
			{
			aLogger.warn ( aMessage );
			}
		else if ( aLogLevel == Level.INFO )
			{
			aLogger.info ( aMessage );
			}
		else if ( aLogLevel == Level.TRACE )
			{
			aLogger.trace ( aMessage );
			}
		else
			{
			aLogger.debug ( aMessage );
			}
		}
	
	/**
	 * @param joinPoint
	 * @param loggable
	 */
	@Before ( value = "logging(loggable)", argNames = "joinPoint,loggable" )
	public void enteringMethod ( JoinPoint joinPoint, Loggable loggable )
		{
		LogIndent.INSTANCE.increment ( );
		
		Signature signature = joinPoint.getSignature ( );
		String className = signature.getDeclaringType ( ).getSimpleName ( );
		String methodName = signature.getName ( );
		Object[] methodParameters = joinPoint.getArgs();

		StringBuilder stringBuilder = new StringBuilder ( );
		stringBuilder.append ( LogIndent.INSTANCE.indent ( ) );
		stringBuilder.append ( "Entering " ).append ( className ).append ( "::" ).append ( methodName ).append ( "(" );
		appendMethodParameters ( stringBuilder, methodParameters );
		stringBuilder.append ( ");" );
		
		Logger logger = LoggerFactory.getLogger ( signature.getDeclaringType ( ) );
		log ( loggable.level ( ), logger, stringBuilder.toString ( ) );
		}
	
	private void appendMethodParameters ( StringBuilder aStringBuilder, Object[] aMethodParameters ) 
		{
		if ( isMethodParameter )
			{
			for ( int i = 0; i < aMethodParameters.length; i++ )
				{
				Object methodParameter = aMethodParameters [ i ];
				aStringBuilder.append ( methodParameter.getClass().getSimpleName() ).append ( "[" ).append ( methodParameter ).append ( "]");
				if ( aMethodParameters.length != ( i + 1 ) )
					{
					aStringBuilder.append ( "," );
					}
				}
			}
		}
	
	/**
	 * @param joinPoint
	 * @param loggable
	 * @param returnValue
	 */
	@AfterReturning ( pointcut = "logging(loggable)", returning = "returnValue", argNames = "joinPoint,loggable,returnValue" )
	public void leavingMethod ( JoinPoint joinPoint, Loggable loggable, Object returnValue )
		{
		Signature signature = joinPoint.getSignature ( );
		String className = signature.getDeclaringType ( ).getSimpleName ( );
		String methodName = signature.getName ( );
		
		StringBuilder stringBuilder = new StringBuilder ( );
		stringBuilder.append ( LogIndent.INSTANCE.indent ( ) );
		stringBuilder.append ( "Leaving " ).append ( className ).append ( "::" ).append ( methodName ).append ( "()" );
		appendReturnValue ( stringBuilder, returnValue );
		stringBuilder.append ( ";" );
		
		Logger logger = LoggerFactory.getLogger ( signature.getDeclaringType ( ) );
		log ( loggable.level ( ), logger, stringBuilder.toString ( ) );
		
		LogIndent.INSTANCE.decrement ( );
		}
	
	private void appendReturnValue ( StringBuilder aStringBuilder, Object returnValue )
		{
		if ( null != returnValue )
			{
			aStringBuilder.append ( ":[" ).append ( returnValue ).append ( "]");
			}
		}
	
	/**
	 * @param joinPoint
	 * @param loggable
	 * @param throwable
	 */
	@AfterThrowing ( pointcut = "logging(loggable)", throwing = "throwable", argNames = "joinPoint,loggable,throwable" )
	public void leavingMethodException ( JoinPoint joinPoint, Loggable loggable, Throwable throwable )
		{
		Signature signature = joinPoint.getSignature ( );
		String className = signature.getDeclaringType ( ).getSimpleName ( );
		String methodName = signature.getName ( );
		String exceptionMessage = throwable.getMessage ( );
		
		StringBuilder stringBuilder = new StringBuilder ( );
		stringBuilder.append ( LogIndent.INSTANCE.indent ( ) );
		stringBuilder.append ( "Leaving " ).append ( className ).append ( "::" ).append ( methodName ).append ( "() " );
		stringBuilder.append ( "on " ).append ( throwable.getClass ( ).getSimpleName ( ) ).append ( ". Reason: " );
		stringBuilder.append ( exceptionMessage );
		
		Logger logger = LoggerFactory.getLogger ( signature.getDeclaringType ( ) );
		log ( loggable.level ( ), logger, stringBuilder.toString ( ) );
		
		LogIndent.INSTANCE.decrement ( );
		}
	
	private static final String METHOD_PARAMETERS_PROPERTY_NAME = "logging.method.parameters";
	private static final String LOGGING_LEVEL_PROPERTY_NAME = "logging.level.min";
	}
