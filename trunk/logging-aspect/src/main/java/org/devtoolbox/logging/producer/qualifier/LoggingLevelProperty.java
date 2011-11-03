package org.devtoolbox.logging.producer.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Retention ( RetentionPolicy.RUNTIME )
@Target ( { ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD } )
@Documented
@Qualifier
public @interface LoggingLevelProperty
	{

	}
