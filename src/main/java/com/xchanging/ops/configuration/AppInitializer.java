package com.xchanging.ops.configuration;

import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	

	public static ServletRegistration.Dynamic registration;
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic reg) {
    	//registration.setMultipartConfig(getMultipartConfigElement());
		
		AppInitializer.registration =reg;
	}

}
