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
package org.devtoolbox.car;

import java.io.File;

import javax.annotation.Resource;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.devtoolbox.car.callback.CallbackBean;
import org.devtoolbox.seat.domain.Seat;
import org.devtoolbox.seat.resource.SeatResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringClientConfiguration;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;

@RunWith(Arquillian.class) 
@SpringClientConfiguration("org/devtoolbox/car/car-context-test.xml") 
public class CarRouteTest
	{
	@Produce(uri = "direct:start")
    protected ProducerTemplate template;
	
	@EndpointInject(uri = "mock:wheelResult")
    protected MockEndpoint wheelResultEndpoint;
	
	@EndpointInject(uri = "mock:suspensionResult")
    protected MockEndpoint suspensionResultEndpoint;
	
	@EndpointInject(uri = "mock:seatResult")
    protected MockEndpoint seatResultEndpoint;
	
	@EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;
	
	@Resource
	private CallbackBean callBackbean;
	
	@Deployment(testable = false) 
	@OverProtocol("Servlet 2.5") 
	public static WebArchive create()
		{
		return ShrinkWrap
				.create(WebArchive.class, "spring-rest-test.war")
				.addClasses(Seat.class, SeatResource.class)
				.addAsLibraries(resolveArtifact(
						"org.springframework:spring-webmvc",
		                "org.codehaus.jackson:jackson-mapper-asl"))
				.addAsWebInfResource("org/devtoolbox/seat/resource/seatResource-web.xml", "web.xml")
				.addAsWebInfResource("org/devtoolbox/seat/resource/seatResource-servlet.xml", "rest-train-servlet.xml")
				.addAsWebInfResource("org/devtoolbox/seat/resource/seatResource-applicationContext.xml", "applicationContext.xml");
		}
	
	@Test @RunAsClient @DirtiesContext
	public void shouldBeAbleToListAllCustomers() throws InterruptedException
		{
		//PREPARE
		wheelResultEndpoint.expectedBodiesReceived("wheelResult");
		suspensionResultEndpoint.expectedBodiesReceived("suspensionResult");
		seatResultEndpoint.expectedBodiesReceived("seatResult");
		resultEndpoint.expectedBodiesReceived("wheelResult+seatResult+suspensionResult");

		//EXECUTE
		template.sendBodyAndHeader("BODY", "carMessageCorrelationId", "carMessageCorrelationId");
		
		//VERIFY
		wheelResultEndpoint.assertIsSatisfied();
		suspensionResultEndpoint.assertIsSatisfied();
		seatResultEndpoint.assertIsSatisfied();
		resultEndpoint.assertIsSatisfied();
		}
	
	public static File[] resolveArtifact(String... artifacts)
		{
		MavenDependencyResolver mvnResolver = DependencyResolvers.use(MavenDependencyResolver.class);
		mvnResolver.loadMetadataFromPom("pom.xml");
		return mvnResolver.artifacts(artifacts).resolveAsFiles();
		}
	}
