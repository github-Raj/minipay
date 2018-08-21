package com.paypal.minipay.config;

import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebAppInitializer  implements WebApplicationInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		servletContext.addListener(new ContextLoaderListener(createWebAppContext()));
		addApacheCxfServlet(servletContext);

	}
	
	private void addApacheCxfServlet(ServletContext servletContext) {
		CXFServlet cxfServlet = new CXFServlet();

		ServletRegistration.Dynamic appServlet = servletContext.addServlet("CXFServlet", cxfServlet);
		appServlet.setLoadOnStartup(1);

		appServlet.addMapping("/*");
	}

	private WebApplicationContext createWebAppContext() {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(WebAppConfig.class);
		return appContext;
	}

}
