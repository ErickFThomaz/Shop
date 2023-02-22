package com.shop.system;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
public class TenantConfiguration {

	@Bean
	public FilterRegistrationBean<TenantFilter> dawsonApiFilter() {
		FilterRegistrationBean<TenantFilter> registration = new FilterRegistrationBean();
		registration.setFilter(new TenantFilter());
		return registration;
	}

	@Bean
	public FilterRegistrationBean<ErrorFilterHandler> errorFilter() {
		FilterRegistrationBean<ErrorFilterHandler> registration = new FilterRegistrationBean<>();
		registration.setFilter(new ErrorFilterHandler());
		registration.setOrder(HIGHEST_PRECEDENCE);
		return registration;
	}

}
