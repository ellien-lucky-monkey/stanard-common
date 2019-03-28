package com.standard.common.utils;

import com.google.common.collect.Maps;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author Jiangkui
 * @date 2019年01月03日
 */
public class EnumUtils {
    /**
     * 获得指定包下面的枚举类
     *
     * @param packageName
     * @return
     */
    public static Set<Class<? extends Enum>> getClassesInPackage(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Enum>> allClasses = reflections.getSubTypesOf(Enum.class);
        return allClasses;
    }

    /**
     * 获得指定包下面的所有的枚举类的值
     *
     * @param packageName
     * @return
     */
    public static Map<String, Object> getAllEnumsInPackage(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Enum>> allClasses = reflections.getSubTypesOf(Enum.class);

        Map<String, Object> result = Maps.newLinkedHashMap();
        allClasses.forEach(t -> {
            Map<String, Object> value = Maps.newLinkedHashMap();

            Arrays.asList(t.getEnumConstants()).forEach(c -> {
                Map<String, Object> fieldValue = Maps.newLinkedHashMap();
                Arrays.stream(c.getClass().getDeclaredFields())
                        .filter(field -> (!field.isEnumConstant()) && !"$VALUES".equals(field.getName()))
                        .forEach(field -> {
                            field.setAccessible(true);
                            try {
                                fieldValue.put(field.getName(), field.get(c));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                value.put(c.toString(), fieldValue);
            });

            char[] chars = t.getSimpleName().toCharArray();
            chars[0] += 32;
            result.put(String.valueOf(chars), value);
        });

        return result;
    }

    public static void main(String[] args) {
        EnumUtils.getAllEnumsInPackage("com.pingan.stand.enums")
                .forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
