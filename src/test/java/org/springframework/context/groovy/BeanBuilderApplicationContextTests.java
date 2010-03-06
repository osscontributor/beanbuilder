package org.springframework.context.groovy;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;


public class BeanBuilderApplicationContextTests extends TestCase {

	public void testLoadingConfigFromClasspath() {
		ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext("file:src/test/resources/org/springframework/context/groovy/applicationContext.groovy");
		
		Object framework = ctx.getBean("framework");
		assertNotNull("could not find framework bean", framework);
		assertEquals("Grails", framework);
	}
}
