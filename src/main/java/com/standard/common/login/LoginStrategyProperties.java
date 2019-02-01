package com.standard.common.login;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jiangkui
 * @since 2019/02/01 10:05
 */
@Component
@ConfigurationProperties(prefix = "login.strategy")
public class LoginStrategyProperties {
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
