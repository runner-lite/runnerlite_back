package ru.runnerlite.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

//@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
	
	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("${driver-class-name}");
		dataSourceBuilder.url("${url}");
		dataSourceBuilder.username("${username}");
		dataSourceBuilder.password("${password}");
		return dataSourceBuilder.build();
	}
}
