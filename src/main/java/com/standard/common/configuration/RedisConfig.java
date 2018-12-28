package com.standard.common.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author ellien
 * @since 2018/12/05 11:42
 */
@Configuration
public class RedisConfig {
    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "application.redis")
    public RedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        return jedisConnectionFactory;
    }
}
