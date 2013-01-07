/*
 * Copyright 2012 step@ <jk@stepat.nl>.
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
package org.devtoolbox.suspension.resource;

import org.devtoolbox.suspension.response.Material;
import org.devtoolbox.suspension.response.Suspension;
import org.devtoolbox.suspension.response.SuspensionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author J. Kazakovs
 *
 */
@Controller
public class SuspensionResource
	{
	@RequestMapping(value="/suspension/{materialValue}", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public SuspensionResponse retrieveSuspension(@PathVariable String materialValue)
		{	
		SuspensionResponse result = new SuspensionResponse();
		
		Material material = new Material();
		material.setType("steel");
		Suspension suspension = new Suspension();
		suspension.setMaterial(material);
		result.setSuspension(suspension);
		
		return result;
		}
	}
