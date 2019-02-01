package com.standard.common.login;

import com.standard.common.domain.User;

/**
 * @author Jiangkui
 * @since 2019/02/01 09:59
 */
public class EmailPasswordStrategy implements LoginStrategy {
    @Override
    public User login(LoginForm form) {
        System.out.println("form = [" + form + "]");
        return null;
    }
}
