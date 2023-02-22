package com.shop.system.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@ConfigurationProperties(prefix = "spring")
public class DataSourceProperties {

	private List<DataSourceProperty> datasources = new ArrayList<>();

}
