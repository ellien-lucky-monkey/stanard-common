package com.standard.common.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private boolean useSuffixPatternMatch = true;


    private boolean useTrailingSlashMatch = true;


    private ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager();

    private final List<String> fileExtensions = new ArrayList<String>();

    private static RequestMapping requestMapping = (RequestMapping) MyRequestMappingHandlerMapping.class.getAnnotation(RequestMapping.class);


    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = null;
        if (requestMapping != null) {
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            info = createRequestMappingInfo(requestMapping, methodCondition, method);
            RequestMapping typeAnnotation = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
            if (typeAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                info = createRequestMappingInfo(typeAnnotation, typeCondition, method).combine(info);
            }
        }
        return info;
    }


    protected RequestMappingInfo createRequestMappingInfo(RequestMapping annotation,
                                                          RequestCondition<?> customCondition, Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String classNameExceptAction = StringUtils.uncapitalize(className.substring(0, className.length() - 10));
        String[] patterns = resolveEmbeddedValuesInPatterns(annotation.value());
        if ((patterns != null) && (patterns.length == 0)) {//
            patterns = new String[]{classNameExceptAction + "!" + method.getName()};
        }
        System.out.println(patterns[0]);

        return new RequestMappingInfo(

                new PatternsRequestCondition(patterns, getUrlPathHelper(), getPathMatcher(), this.useSuffixPatternMatch,
                        this.useTrailingSlashMatch, this.fileExtensions),
                new RequestMethodsRequestCondition(annotation.method()),
                new ParamsRequestCondition(annotation.params()), new HeadersRequestCondition(annotation.headers()),
                new ConsumesRequestCondition(annotation.consumes(), annotation.headers()), new ProducesRequestCondition(
                annotation.produces(), annotation.headers(), this.contentNegotiationManager),
                customCondition);
    }
}