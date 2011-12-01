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

package org.devtoolbox.errorhandling;

import org.apache.camel.Exchange;
import org.devtoolbox.errorhandling.exception.FileMoverException;

/**
 * Used to move a file being processed by a route from processing to error folder.
 * 
 * Implementation relies on the following properties set in Apache Camel {@link Exchange}:
 * <ul>
 * 	<li>originalFileName - the name of the processed file;</li>
 * 	<li>rootFolderPath - processing root folder;</li>
 * 	<li>processingFolderPath - folder to store files being processed;</li>
 * 	<li>errorFolderPath - folder to store files for which processing has failed;</li>
 * </ul>
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
public interface FileMover {

	/**
	 * Move file from error processing to error folder.
	 * 
	 * @param anExchange
	 * @throws {@link FileMoverException} 
	 * <ul>
	 * 	<li>if either of the required properties are not specified;</li>
	 * 	<li>if moving of the file from processing folder to archive folder has failed;</li>
	 * </ul>
	 */
	public void move(Exchange exchange) throws FileMoverException;
}
