package com.standard.common.tree;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * @author Jiangkui
 * @since 2019/02/22 10:55
 */
public class TreeUtil {
    public static <T> List<T> getChildList(List<T> list, Long pid, List<T> reList, Class<T> clazz) {
        for (T obj : list) {
            if (getParentId(obj, clazz).equals(pid)) {
                reList.add(obj);
                if (hasChild(list, clazz, getId(obj, clazz))) {
                    getChildList(list, getId(obj, clazz), reList, clazz);
                }
            }
        }
        return reList;
    }

    private static <T> boolean hasChild(List<T> list, Class<T> clazz, Long pid) {
        for (T obj : list) {
            if (getParentId(obj, clazz).equals(pid)) {
                return true;
            }
        }
        return false;
    }

    private static <T> Long getId(T obj, Class<T> clazz) {
        Object property = getProperty(obj,clazz, "id");
        return Objects.nonNull(property) ? (Long) property : 0L;
    }


    private static <T> Long getParentId(T obj, Class<T> clazz) {
        Object property = getProperty(obj,clazz, "parentId");
        return Objects.nonNull(property) ? (Long) property : 0L;
    }

    private static <T> Object getProperty(T obj, Class<T> clazz, String property) {
        Field field = getFiled(clazz, property);
        field.setAccessible(true);
        Object val = null;
        try {
            val = field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return val;
    }

    private static <T> Field getFiled(Class<T> clazz, String property) {
        return ReflectionUtils.findField(clazz, property);
    }
}
