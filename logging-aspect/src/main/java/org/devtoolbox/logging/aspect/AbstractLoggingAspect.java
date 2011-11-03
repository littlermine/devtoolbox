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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.devtoolbox.logging.aspect.helper.LoggingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 * 
 */
public @Aspect abstract class AbstractLoggingAspect
	{
	private LoggingHelper loggingHelper;
	
	public AbstractLoggingAspect ( )
		{
		loggingHelper = LoggingHelper.newInstance ( );
		}
	
	@Pointcut
	public abstract void logging ( );

	@Before ( value = "logging()", argNames = "joinPoint" )
	public void enteringMethod ( JoinPoint joinPoint )
		{
		String message = loggingHelper.constructEnteringMethodMessage ( joinPoint );
		logger.debug ( message );
		}	
	
	@AfterReturning ( pointcut = "logging()", returning = "returnValue", argNames = "joinPoint,returnValue" )
	public void leavingMethod ( JoinPoint joinPoint, Object returnValue )
		{
		String message = loggingHelper.constructLeavingMethodMessage ( joinPoint, returnValue );
		logger.debug ( message );
		}
	
	@AfterThrowing ( pointcut = "logging()", throwing = "throwable", argNames = "joinPoint,throwable" )
	public void leavingMethodException ( JoinPoint joinPoint, Throwable throwable )
		{
		String message = loggingHelper.constructLeavingMethodExceptionMessage ( joinPoint, throwable );
		logger.debug ( message );
		}

	private static Logger logger = LoggerFactory.getLogger ( AbstractLoggingAspect.class );
	}
