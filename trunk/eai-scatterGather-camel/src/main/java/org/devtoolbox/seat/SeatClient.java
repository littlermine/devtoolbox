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
package org.devtoolbox.seat;

import org.devtoolbox.seat.domain.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author J. Kazakovs
 * 
 */
@Component public class SeatClient
	{
	@Autowired private RestTemplate restTemplate;
	@Value("${url}") private String url;

	public String retrieveSeatStatus()
		{
		String result = null;

		String resourceUrl = url + "/seat";
		Seat seat = restTemplate.getForObject(resourceUrl, Seat.class);
		result = seat.getStatus();

		return result;
		}
	}
