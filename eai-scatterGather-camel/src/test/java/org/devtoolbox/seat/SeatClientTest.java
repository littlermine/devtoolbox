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

import java.io.File;
import java.net.URL;

import javax.annotation.Resource;

import org.devtoolbox.seat.SeatClient;
import org.devtoolbox.seat.domain.Seat;
import org.devtoolbox.seat.resource.SeatResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringClientConfiguration;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author J. Kazakovs
 * 
 */
@RunWith(Arquillian.class) 
@SpringClientConfiguration("org/devtoolbox/seat/seatClient-context.xml") 
public class SeatClientTest
	{
	@Resource private SeatClient unitUnderTest;

	@ArquillianResource private URL contextPath;

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

	@Test @RunAsClient
	public void shouldBeAbleToListAllCustomers()
		{
		//EXECUTE
		String result = unitUnderTest.retrieveSeatStatus();
		
		//VERIFY
		Assert.assertNotNull(result);
		Assert.assertEquals("OK", result);
		}

	public static File[] resolveArtifact(String... artifacts)
		{
		MavenDependencyResolver mvnResolver = DependencyResolvers.use(MavenDependencyResolver.class);
		mvnResolver.loadMetadataFromPom("pom.xml");
		return mvnResolver.artifacts(artifacts).resolveAsFiles();
		}
	}
