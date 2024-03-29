/*
 * Copyright 2013 step@ <jk@stepat.nl>.
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
package org.devtoolbox.car.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

/**
 * @author J. Kazakovs
 *
 */
@Component("carAggregationStrategy")
public class CarAggregationStrategy implements AggregationStrategy
	{
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange)
		{
		// the first time we only have the new exchange
		if (oldExchange == null)
			{
			return newExchange;
			}

		Object bodyNew = newExchange.getIn().getBody();
		Object bodyOld = oldExchange.getIn().getBody();
		
		newExchange.getIn().setBody(bodyOld + "+" + bodyNew);
		
		return newExchange;
		}
	}
