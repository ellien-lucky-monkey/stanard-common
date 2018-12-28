package com.standard.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class StandardCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(StandardCommonApplication.class, args);
    }
}
