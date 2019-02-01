package com.standard.common.login;

import com.standard.common.domain.User;

/**
 * @author Jiangkui
 * @since 2019/02/01 09:55
 */
public interface LoginStrategy {
    User login(LoginForm form);
}
