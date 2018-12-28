//package com.standard.common.configuration;
//
//import org.apache.tomcat.util.http.LegacyCookieProcessor;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author ellien
// * @since 2018/12/05 14:49
// */
//@Configuration
//public class CookieConfig {
//    @Bean
//    public EmbeddedServletContainerCustomizer customizer(){
//        return container -> {
//            TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
//            tomcat.addContextCustomizers(
//                    (TomcatContextCustomizer) context -> context.setCookieProcessor(new LegacyCookieProcessor()));
//        };
//    }
//}
