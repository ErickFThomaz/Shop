package com.shop.system;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static org.springframework.util.StringUtils.isEmpty;

public class TenantFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String tenantId = httpServletRequest.getHeader("TENANT");
		if (isEmpty(tenantId)) {
			TenantLocalStorage.setTenantName("loja1".toUpperCase());
		}
		else {
			TenantLocalStorage.setTenantName(tenantId);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

}
