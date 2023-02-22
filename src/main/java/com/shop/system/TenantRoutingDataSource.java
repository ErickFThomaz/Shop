package com.shop.system;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import javax.sql.DataSource;

import static com.shop.system.TenantLocalStorage.getTenantName;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return getTenantName();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		Assert.notNull(super.getResolvedDataSources(), "DataSource router not initialized");
		Object lookupKey = determineCurrentLookupKey();

		if (lookupKey == null) {
			lookupKey = "LOJA1";
		}

		DataSource dataSource = this.getResolvedDataSources().get(lookupKey);
		if (dataSource == null) {
			dataSource = this.getResolvedDefaultDataSource();
		}
		if (dataSource == null) {
			throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
		}
		return dataSource;
	}

}
