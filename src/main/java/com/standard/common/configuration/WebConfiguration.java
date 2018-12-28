package com.standard.common.configuration;

//import com.pingan.haofang.gov.sm.account.interceptor.AuthorizationInterceptor;

import com.standard.common.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author ellien
 * @since 2018/12/03 20:02
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {


    @Autowired
    private SessionInterceptor sessionInterceptor;


//    @Autowired
//    private AuthorizationInterceptor authorizationInterceptor;
//
    /**
     * 拦截器配置
     *
     * @param registry 注册类
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/modules/api/**");
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/session/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // swagger
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
