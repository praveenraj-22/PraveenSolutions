package com.project2.DBconfig;


import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.project2.Filter.CORSFilter;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	protected Class<?>[] getRootConfigClasses() {

		return new Class[] { Dbconfig.class };
	}

	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { WebConfig.class };
	}

	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	protected Filter[] getServletFilters() {
		System.out.println("Initializing filter");
		Filter[] singleton = { new CORSFilter() };
		System.out.println("Filter initialized successfully");
		return singleton;
	}
}
