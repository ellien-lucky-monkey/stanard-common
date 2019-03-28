package com.standard.common.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Jiangkui
 * @date 2019年03月28日
 */
@Component
public class UrlBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(UrlBeanPostProcessor.class);
    private static final String defaultUrlPrefix = "/common/api";

    @Value("${standard.common.url.prefix:/common/api}")
    public String urlPrefix;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = getClass(bean);
        Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods).filter(method -> clazz.isAnnotationPresent(RestController.class) && method.isAnnotationPresent(RequestMapping.class)).forEach(method -> {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            String path = requestMapping.value()[0];
            if (!defaultUrlPrefix.equals(urlPrefix) && path.startsWith(defaultUrlPrefix)) {
                path = path.replace(defaultUrlPrefix, urlPrefix);
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(requestMapping);
                Field declaredField = null;
                try {
                    declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
                    declaredField.setAccessible(true);
                    Map memberValues = (Map) declaredField.get(invocationHandler);
                    // 修改 value 属性值
                    memberValues.put("value", new String[]{path});
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    logger.info("requestMapping.value.modify.faill", e);
                    e.printStackTrace();
                }
            }
        });
        return bean;
    }

    private Class getClass(Object bean) {
        if (ClassUtils.isCglibProxy(bean)) {
            return bean.getClass().getSuperclass();
        }
        return bean.getClass();
    }
}
