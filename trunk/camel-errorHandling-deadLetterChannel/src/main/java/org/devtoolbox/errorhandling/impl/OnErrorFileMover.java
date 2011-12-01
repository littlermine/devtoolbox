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

package org.devtoolbox.errorhandling.impl;

import java.io.File;
import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.devtoolbox.errorhandling.FileMover;
import org.devtoolbox.errorhandling.exception.FileMoverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
@Component("onErrorFileMover")
public class OnErrorFileMover implements FileMover {

	//--- PUBLIC ---// 
	
	/**
	 * {@inheritDoc}
	 *
	 * @see org.devtoolbox.errorhandling.FileMover#move(org.apache.camel.Exchange)
	 */
	public void move(Exchange anExchange) throws FileMoverException {

		final String fileSeparator = System.getProperty("file.separator");
		
		String fileName = retrieveOriginalFileName(anExchange);
		String rootFolderPath = retrieveRootFolderPath(anExchange, fileName);
		String processingFolderPath = retrieveProcessingFolderPath(anExchange, fileName);
		String errorFolderPath = retrieveErrorFolderPath(anExchange, fileName);
		String processingPath = rootFolderPath + fileSeparator + processingFolderPath + fileSeparator + fileName;
		File processingFile = new File(processingPath);
		if (processingFile.exists()) {

			String errorPath = rootFolderPath + fileSeparator + errorFolderPath;
			File errorDirectory = new File(errorPath);
			boolean createDirectory = (!errorDirectory.exists());
			try {
				
				FileUtils.moveFileToDirectory(processingFile, errorDirectory, createDirectory);
			} catch (IOException e) {
				
				final String errorMsg = ""; //TODO add error message
				throw new FileMoverException(errorMsg);
			}
		}
	}
	
	//--- PRIVATE ---//
	
	private String retrieveErrorFolderPath(Exchange anExchange, String aFileName) {
		String result = null;
		
		result = anExchange.getProperty("errorFolderPath", String.class);
		if(StringUtils.isEmpty(result)) {
			
			final String errorMsg = "Error handling route is not able to move \"" + aFileName + "\" file from a processing to an error folder, because the error folder path is not known.";
			logger.error(errorMsg);
			throw new FileMoverException(errorMsg);
		}

		return result;
	}
	
	private String retrieveProcessingFolderPath(Exchange anExchange, String aFileName) {
		String result = null;
		
		result = anExchange.getProperty("processingFolderPath", String.class);
		if(StringUtils.isEmpty(result)) {
			
			final String errorMsg = "Error handling route is not able to move \"" + aFileName + "\" file from a processing to an error folder, because the processing folder path is not known.";
			logger.error(errorMsg);
			throw new FileMoverException(errorMsg);
		}

		return result;
	}
	
	private String retrieveRootFolderPath(Exchange anExchange, String aFileName) {
		String result = null;
		
		result = anExchange.getProperty("rootFolderPath", String.class);
		if(StringUtils.isEmpty(result)) {
			
			final String errorMsg = "Error handling route is not able to move \"" + aFileName + "\" file from a processing to an error folder, because the root folder path is not known.";
			logger.error(errorMsg);
			throw new FileMoverException(errorMsg);
		}

		return result;
	}
	
	private String retrieveOriginalFileName(Exchange anExchange) {
		String result = null;
		
		result = anExchange.getProperty("originalFileName", String.class);
		if(StringUtils.isEmpty(result)) {
			
			final String errorMsg = "Error handling route is not able to move a file from a processing to an error folder, because the name of a file to move is not known.";
			logger.error(errorMsg);
			throw new FileMoverException(errorMsg);
		}

		return result;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(OnErrorFileMover.class);
}
