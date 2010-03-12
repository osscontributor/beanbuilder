/*
* Copyright 2010 the original author or authors.
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*      http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.springframework.context.groovy;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;

public class BeanBuilderApplicationContextTests extends TestCase {
    
    /*
    NOTE: The code in this test case includes casts that look a bit funky...
    ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext(resources);
    
    Important to note is that the cast is only necessary because this test is being compiled
    along with BeanBuilderApplicationContext.  This is related to a limitation in groovyc with respect
    to AST transformations, in this case @Delegate.
    
    Normal users of BeanBuilderApplicationContext will not need to impose this cast
    because the compiled BeanBuilderApplicationContext class will in fact implement ApplicationContext.
    */
    
    
    public void testLoadingMultipleConfigFiles() {
        String[] resources = new String[] {            
            "org/springframework/context/groovy/applicationContext2.groovy",
            "org/springframework/context/groovy/applicationContext.groovy" };
        ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext(resources);

        Object framework = ctx.getBean("framework");
        assertNotNull("could not find framework bean", framework);
        assertEquals("Grails", framework);

        Object company = ctx.getBean("company");
        assertNotNull("could not find company bean", company);
        assertEquals("SpringSource", company);
    }

    public void testLoadingConfigFile() {
        ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext(
            "org/springframework/context/groovy/applicationContext.groovy");

        Object framework = ctx.getBean("framework");
        assertNotNull("could not find framework bean", framework);
        assertEquals("Grails", framework);
    }

    public void testLoadingConfigFileRelativeToClass() {
        ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext(
            "applicationContext.groovy", BeanBuilderApplicationContextTests.class);

        Object framework = ctx.getBean("framework");
        assertNotNull("could not find framework bean", framework);
        assertEquals("Grails", framework);
    }

    public void testLoadingMultipleConfigFilesRelativeToClass() {
        String[] resources = new String[] {            
            "applicationContext2.groovy",
            "applicationContext.groovy" };
        ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext(resources, BeanBuilderApplicationContextTests.class);

        Object framework = ctx.getBean("framework");
        assertNotNull("could not find framework bean", framework);
        assertEquals("Grails", framework);

        Object company = ctx.getBean("company");
        assertNotNull("could not find company bean", company);
        assertEquals("SpringSource", company);
    }

    public void testLoadingConfigFileWithFileReference() {
        ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext(
            "file:src/test/resources/org/springframework/context/groovy/applicationContext.groovy");

        Object framework = ctx.getBean("framework");
        assertNotNull("could not find framework bean", framework);
        assertEquals("Grails", framework);
    }

    public void testLoadingMultipleConfigFilesWithFileReferences() {
        String[] resources = new String[] {
            "file:src/test/resources/org/springframework/context/groovy/applicationContext2.groovy",
            "file:src/test/resources/org/springframework/context/groovy/applicationContext.groovy" };
        ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext(resources);

        Object framework = ctx.getBean("framework");
        assertNotNull("could not find framework bean", framework);
        assertEquals("Grails", framework);

        Object company = ctx.getBean("company");
        assertNotNull("could not find company bean", company);
        assertEquals("SpringSource", company);
    }

    public void testLoadingMultipleConfigFilesWithFileAndClasspathReferences() {
        String[] resources = new String[] {
            "file:src/test/resources/org/springframework/context/groovy/applicationContext2.groovy",
            "classpath:org/springframework/context/groovy/applicationContext.groovy" };
        ApplicationContext ctx = (ApplicationContext) new BeanBuilderApplicationContext(resources);

        Object framework = ctx.getBean("framework");
        assertNotNull("could not find framework bean", framework);
        assertEquals("Grails", framework);

        Object company = ctx.getBean("company");
        assertNotNull("could not find company bean", company);
        assertEquals("SpringSource", company);
    }
}
