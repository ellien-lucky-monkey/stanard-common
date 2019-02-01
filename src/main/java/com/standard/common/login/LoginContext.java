package com.standard.common.login;

import lombok.Value;

/**
 * @author Jiangkui
 * @since 2019/02/01 10:02
 */
@Value
public class LoginContext {

    private LoginStrategy loginStrategy;

    public LoginContext(LoginStrategy loginStrategy){
        this.loginStrategy = loginStrategy;
    }
}
