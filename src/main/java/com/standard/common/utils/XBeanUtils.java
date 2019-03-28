package com.standard.common.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import static org.apache.commons.beanutils.BeanUtils.copyProperty;

/**
 * @author Jiangkui
 * @date 2019年01月03日
 */
public class XBeanUtils extends BeanUtils {

    private static final String JAVA_CLASS = "class";
    private static final String JAVA_SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 对象间属性信息copy
     *
     * @param dest     目标对象
     * @param orig     源对象
     * @param copyNull 是否复制NULL属性，true:复制 false:不复制
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyProperties(Object dest, Object orig, boolean copyNull)
            throws IllegalAccessException, InvocationTargetException {
        copyProperties(dest, orig, copyNull, true, null);
    }

    public static void copyPropertiesIgnoresEmpty(Object dest, Object orig)
            throws IllegalAccessException, InvocationTargetException {
        copyProperties(dest, orig, false, false, null);
    }

    /**
     * 对象间属性信息copy
     *
     * @param dest         目标对象
     * @param orig         源对象
     * @param copyNull     是否复制NULL属性，true:复制 false:不复制
     * @param fieldIgnores 忽略的字段数组
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("rawtypes")
    public static void copyProperties(Object dest, Object orig, boolean copyNull, boolean copyEmpty,
                                      String[] fieldIgnores) throws IllegalAccessException, InvocationTargetException {
        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        // 是map对象
        if (orig instanceof Map) {
            Map origMap = (Map) orig;
            for (Object o : origMap.keySet()) {
                String name = o.toString();
                if (JAVA_CLASS.equals(name) || JAVA_SERIAL_VERSION_UID.equals(name)) {
                    continue;
                }
                Object value = origMap.get(name);
                boolean canCopyValue = canCopy(value, name, copyNull, copyEmpty, fieldIgnores);
                if (canCopyValue) {
                    copyProperty(dest, name, value);
                }
            }
            return;
        }

        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(orig);
        for (PropertyDescriptor origDescriptor : origDescriptors) {
            if (origDescriptor.getReadMethod() == null) {
                continue;
            }
            String name = origDescriptor.getName();
            if (JAVA_CLASS.equals(name) || JAVA_SERIAL_VERSION_UID.equals(name)) {
                continue;
            }
            Object value = null;
            try {
                value = PropertyUtils.getSimpleProperty(orig, name);
            } catch (NoSuchMethodException e) {
                value = null;
            }
            boolean canCopyValue = canCopy(value, name, copyNull, copyEmpty, fieldIgnores);
            if (canCopyValue) {
                copyProperty(dest, name, value);
            }
        }
    }

    private static boolean canCopy(Object value, String fieldName, boolean copyNull, boolean copyEmpty,
                                   String[] fieldIgnores) {
        // (1) fieldIgnores 包含了字段直接不能copy
        if (ArrayUtils.contains(fieldIgnores, fieldName)) {
            return false;
        }

        // 当不允许复制null，但字段值为null时，直接false
        if (!copyNull && value == null) {
            return false;
        }

        // 当不允许复制""，但字段值为""时，直接false
        if (value instanceof String && !copyEmpty && StringUtils.isEmpty(value.toString())) {
            return false;
        }

        // 当不允许复制 空 Collection，但字段值为 空 Collection时，直接false
        if (value instanceof Collection && !copyEmpty && CollectionUtils.isEmpty((Collection) value)) {
            return false;
        }

        // 当不允许复制 空 Map，但字段值为 空 Map时，直接false
        if (value instanceof Map && !copyEmpty && MapUtils.isEmpty((Map) value)) {
            return false;
        }

        return true;
    }
}
