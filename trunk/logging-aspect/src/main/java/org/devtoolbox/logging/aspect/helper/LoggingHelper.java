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
package org.devtoolbox.logging.aspect.helper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

/**
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
public class LoggingHelper
	{
	public static LoggingHelper newInstance ( ) 
		{
		return new LoggingHelper ( );
		}
	
	public String constructEnteringMethodMessage ( JoinPoint joinPoint )
		{
		String result = null;
		
		Signature signature = joinPoint.getSignature ( );
		String className = signature.getDeclaringType ( ).getSimpleName ( );
		String methodName = signature.getName ( );
		result = String.format ( "enteringMethod %s::%s", className, methodName );
		
		return result;
		}
	
	public String constructLeavingMethodMessage ( JoinPoint joinPoint, Object returnValue )
		{
		String result = null;
		
		Signature signature = joinPoint.getSignature ( );
		String className = signature.getDeclaringType ( ).getSimpleName ( );
		String methodName = signature.getName ( );
		result = String.format ( "leavingMethod %s::%s", className, methodName );
		
		return result;
		}
	
	public String constructLeavingMethodExceptionMessage ( JoinPoint joinPoint, Throwable throwable )
		{
		String result = null;
		
		Signature signature = joinPoint.getSignature ( );
		String className = signature.getDeclaringType ( ).getSimpleName ( );
		String methodName = signature.getName ( );
		String exceptionMessage = throwable.getMessage ( );
		result = String.format ( "leavingMethodException %s::%s. Reason: %s", className, methodName, exceptionMessage );
		
		return result;
		}
	}
