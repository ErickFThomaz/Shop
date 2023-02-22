package com.shop.system;

import com.shop.core.auditing.AuditingDateTimeProvider;
import com.shop.core.auditing.SpringSecurityAuditor;
import com.shop.system.properties.DataSourceProperties;
import com.shop.system.properties.DataSourceProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.time.Clock;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = { "com.shop" })
@EnableJpaRepositories(basePackages = { "com.shop" })
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider", auditorAwareRef = "auditorProvider")
public class DomainConfiguration {

	@Bean
	public DateTimeProvider dateTimeProvider(Clock systemClock) {
		return new AuditingDateTimeProvider(systemClock);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new SpringSecurityAuditor();
	}

	@Bean
	public Clock systemClock() {
		return Clock.system(ZoneId.of("America/Sao_Paulo"));
	}

	@Bean
	@Primary
	public DataSource dateSource(DataSourceProperties source) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		AbstractRoutingDataSource tenantRoutingDataSource = new TenantRoutingDataSource();
		Map<String, List<DataSourceProperty>> dsGrouped = source.getDatasources().stream()
				.collect(groupingBy(DataSourceProperty::getDbInstance));

		dsGrouped.forEach((instance, dataSourceProperties) -> {
			DataSourceProperty ds = dataSourceProperties.stream()
					.filter(dataSourceProperty -> dataSourceProperty.getDbInstance().equals(instance)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException("Ops! Tem coisa errada ai :("));

			DataSource dataSource = DataSourceBuilder.create().url(ds.getUrl()).username(ds.getUsername())
					.password(ds.getPassword()).driverClassName(ds.getDriverClassName()).build();

			dataSourceProperties.forEach(dsProperty -> targetDataSources.put(dsProperty.getName(), dataSource));
		});

		tenantRoutingDataSource.setTargetDataSources(targetDataSources);
		tenantRoutingDataSource.afterPropertiesSet();
		return tenantRoutingDataSource;
	}

}
