package com.web.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by alex on 13.02.18.
 */
@Configuration
@EntityScan("com.web.model")
@ComponentScan({"com.web.persistent", "com.web.service"})
@EnableJpaRepositories(basePackages = {"com.web.persistent"})
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaConfig {
}
