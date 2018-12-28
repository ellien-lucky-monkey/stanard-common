package com.standard.common.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ellien
 * @since 2018/12/05 17:52
 */
@Configuration
@ConditionalOnProperty("spring.datasource.account.url")
public class DatasourceConfig {

        @Bean(name = "accountDataSource", destroyMethod = "close", initMethod = "init")
        @ConditionalOnMissingBean(DataSource.class)
        @ConfigurationProperties("spring.datasource.account")
        public DataSource dataSource() {
            return new DruidDataSource();
        }

}
