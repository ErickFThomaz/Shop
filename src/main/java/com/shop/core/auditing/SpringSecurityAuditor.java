package com.shop.core.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static java.util.Objects.nonNull;

public class SpringSecurityAuditor implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		SecurityContext context = SecurityContextHolder.getContext();
		String user = "UNKNOWN";
		if (nonNull(context.getAuthentication())) {
			if (!context.getAuthentication().getName().equalsIgnoreCase("anonymousUser")) {
				user = context.getAuthentication().getName();
			}
		}
		return Optional.of(user);
	}

}