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
package org.devtoolbox;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 
 * @author Janis Kazakovs <janis.kazakovs@opatopa.com>
 *
 */
public class BasketTest {

	private Basket unitUnderTest;
	
	public @BeforeClass void setUp ( ) {
		
		WeldContainer weld = new Weld ( ).initialize ( );
		unitUnderTest = weld.instance ( ).select ( Basket.class ).get ( );
	}
	
	public @Test void test ( ) {
		
		unitUnderTest.catchTheBall();
	}
}
