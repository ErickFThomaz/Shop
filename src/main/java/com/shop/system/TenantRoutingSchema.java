package com.shop.system;

import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TenantRoutingSchema implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {

	@Override
	public String resolveCurrentTenantIdentifier() {
		if (StringUtils.hasText(TenantLocalStorage.getTenantName())) {
			return TenantLocalStorage.getTenantName();
		}
		return "loja1";
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
	}

}
