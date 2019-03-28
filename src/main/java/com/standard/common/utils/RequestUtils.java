package com.standard.common.utils;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jiangkui
 * @date 2018年12月28日
 */
public class RequestUtils {
    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Assert.notNull(requestAttributes, "currentRequestAttributes is null");
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static HttpSession getCurrentSession() {
        return getCurrentRequest().getSession();
    }

    public static Cookie[]  getCookies() {
        return  getCurrentRequest().getCookies();
    }


    public void updateCookie(HttpServletRequest request, HttpServletResponse response, String domain){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("SESSION-ACCOUNT-B".equals(cookie.getName())) {
                cookie.setMaxAge(7 * 24 * 3600);
                cookie.setDomain(domain);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
        }
    }
}
