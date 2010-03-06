package org.springframework.context.groovy;


import org.springframework.context.ApplicationContext 

class BeanBuilderApplicationContext {
	
	@Delegate
	private ApplicationContext applicationContext
	
	
	public BeanBuilderApplicationContext(String string) {
		def bb = new BeanBuilder()
		bb.loadBeans string
		
		
//		springGroovyResourcesBeanBuilder = new BeanBuilder(null, config,Thread.currentThread().getContextClassLoader());
//		Script script = (Script) groovySpringResourcesClass.newInstance();
//		script.run();
//		Object beans = script.getProperty("beans");
//		springGroovyResourcesBeanBuilder.beans((Closure) beans);
		
		
		applicationContext = bb.createApplicationContext()
	}
}
