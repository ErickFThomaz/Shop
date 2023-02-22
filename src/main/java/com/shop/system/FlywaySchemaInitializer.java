package com.shop.system;

import com.shop.system.properties.DataSourceProperties;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

@Configuration
@AllArgsConstructor
public class FlywaySchemaInitializer {

	private final TenantRoutingDataSource dataSource;

	private final DataSourceProperties dataSourceProperties;

	@PostConstruct
	public void migrateFlyway() {
		dataSourceProperties.getDatasources().forEach(dataSourceProperty -> {
			TenantLocalStorage.setTenantName(dataSourceProperty.getName());
			Flyway flyway = Flyway.configure().dataSource(dataSource).load();
			flyway.migrate();
		});
		TenantLocalStorage.setTenantName("loja1".toUpperCase());
	}

}