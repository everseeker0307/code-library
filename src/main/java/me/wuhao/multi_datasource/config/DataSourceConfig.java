package me.wuhao.multi_datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by everseeker on 2017/9/1.
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "hvrDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.postgresql.hvr")
    public DataSource hvrDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "wuhaoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql.wuhao")
    public DataSource wuhaoDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "hvrJdbcTemplate")
    @Primary
    public JdbcTemplate hvrJdbcTemplate(@Qualifier("hvrDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "wuhaoJdbcTemplate")
    public JdbcTemplate wuhaoJdbcTemplate(@Qualifier("wuhaoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
