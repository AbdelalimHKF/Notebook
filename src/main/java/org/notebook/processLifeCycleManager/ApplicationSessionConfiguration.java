package org.notebook.processLifeCycleManager;

import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

public class ApplicationSessionConfiguration {

	@Bean
	public ServletListenerRegistrationBean<HttpSessionListener> sessionListener() {
		return new ServletListenerRegistrationBean<HttpSessionListener>(new ProcessLifeCycleManager());
	}
}
