package com.standard.common.interceptor;

import com.pingan.haofang.gov.sm.account.common.exception.AccessForbiddenException;
import com.pingan.haofang.gov.sm.account.entity.dto.account.AccountDTO;
import com.pingan.haofang.gov.sm.account.entity.dto.account.AccountJWTInfoDTO;
import com.pingan.haofang.gov.sm.account.rpc.account.IAccountJWTRPCService;
import com.pingan.haofang.gov.sm.account.service.account.IAccountJWTService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author Jiangkui
 * @since 2019/01/29 14:40
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    @Autowired
    private IAccountJWTService accountJWTService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String appId = "";
        Integer sourceType = 2;
        String ticket = request.getParameter("ticket");
        HttpSession session = request.getSession();
        //step-1 通过ticket获取用户信息
        //step-2 设置session
        //step-3 判断登录
        if (StringUtils.isNotEmpty(ticket)) {
            AccountJWTInfoDTO accountJWTInfo = accountJWTService.getJwtInfoByTicket(appId, sourceType, ticket);
            if (Objects.isNull(accountJWTInfo)) {
                final String msg = "user.not.login";
                throw new AccessForbiddenException(msg);
            }
            AccountDTO account = accountJWTInfo.getAccountDTO();
            session.setAttribute("accountId",account.getId());
            session.setAttribute("sourceType",account.getSourceType());
        }

        Object accountId = session.getAttribute("accountId");
        if (Objects.isNull(accountId)) {
            final String msg = "user.not.login";
            throw new AccessForbiddenException(msg);
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
