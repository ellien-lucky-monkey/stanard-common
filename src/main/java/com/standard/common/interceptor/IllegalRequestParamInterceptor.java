package com.standard.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author Jiangkui
 * @date 2019年01月04日
 */
@Component
public class IllegalRequestParamInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求所有参数，校验防止脚本注入，防止XSS漏洞
        Enumeration<?> params = request.getParameterNames();
        String paramN = null;
        while (params.hasMoreElements()) {
            paramN = (String) params.nextElement();
            String paramVale = request.getParameter(paramN);
            //if (!paramN.toLowerCase().contains("password")) {
            //}
            // 校验是否存在SQL注入信息
            if (checkSQLInject(paramVale)) {
                throw new RuntimeException("request.params.has.specialCharacter");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // DO NOTHING
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // DO NOTHING
    }

    /**
     * 检查是否存在非法字符，防止js脚本注入
     *
     * @param str 被检查的字符串
     * @return ture-字符串中存在非法字符，false-不存在非法字符
     */
    private boolean checkSQLInject(String str) {
        // 如果传入空串则认为不存在非法字符
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        // 判断黑名单
        String[] inj_stra = {"script", "src", "mid", "master", "truncate", "insert", "select", "delete", "update", "declare",
                "iframe", "onreadystatechange", "alert", "atestu", "xss", ";", "'", "\"", "<", ">", "+",
                "\\", "svg", "confirm", "prompt", "onload", "onmouseover", "onfocus", "onerror"};
        // sql不区分大小写
        str = str.toLowerCase();
        for (int i = 0; i < inj_stra.length; i++) {
            if (str.indexOf(inj_stra[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

}
