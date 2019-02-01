package com.standard.common.login;

import com.standard.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiangkui
 * @since 2019/02/01 09:53
 */
@Service
public class LoginService {
    @Autowired
    private LoginFactory loginFactory;

    public User login(LoginForm form){
        return loginFactory.getContext().getLoginStrategy().login(form);
    }

}
