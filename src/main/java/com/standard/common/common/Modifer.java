package com.standard.common.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Component
public class Modifer implements CommandLineRunner {

    @Value("${standard.account.url.prefix:/modules/api}")
    private String urlPrefix;

    public void run(String... args) throws Exception {

       if (StringUtils.isEmpty(urlPrefix)) {return;}
        System.out.println(Constans.api);
        //获取Bean类的INT_VALUE字段
        Field field = Constans.class.getField("api");
        //将字段的访问权限设为true：即去除private修饰符的影响
        field.setAccessible(true);
        /*去除final修饰符的影响，将字段设为可修改的*/
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        //把字段值设为200
        field.set(null, urlPrefix);
        System.out.println(Constans.api);

        intValue();

    }


    public void intValue() throws Exception {
        System.out.println(Constans.int_value);
        //获取Bean类的INT_VALUE字段
        Field field = Constans.class.getField("int_value");
        //将字段的访问权限设为true：即去除private修饰符的影响
        field.setAccessible(true);
        /*去除final修饰符的影响，将字段设为可修改的*/
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        //把字段值设为200
        field.set(null, 200);
        System.out.println(Constans.int_value);
    }
}
