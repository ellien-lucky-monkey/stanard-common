package com.standard.common.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jiangkui
 * @since 2019/02/01 10:05
 */
@Component
public class LoginFactory {

    @Autowired
    private LoginStrategyProperties loginStrategyProperties;

    public LoginContext getContext() {
        Integer type = loginStrategyProperties.getType();
        switch (type) {
            case 1:
                return new LoginContext(new UsernamePasswordStrategy());
            case 2:
                return new LoginContext(new EmailPasswordStrategy());
            default:
                break;
        }
        return null;
    }
}
