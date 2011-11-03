package org.devtoolbox.dummy;

import org.devtoolbox.logging.aspect.annotation.Loggable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDummy
	{
	private static Logger logger = LoggerFactory.getLogger ( LoggingDummy.class );

	@Loggable public void foo ( )
		{
		logger.debug ( "foo" );
		}

	@Loggable public String fooReturning ( )
		{
		logger.debug ( "fooReturning" );
		return "returnValue";
		}

	@Loggable public void fooException ( )
		{
		logger.debug ( "foo" );
		throw new UnsupportedOperationException ( "error() method is not supported" );
		}
	}
