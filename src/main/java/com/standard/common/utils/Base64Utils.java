package com.standard.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author Jiangkui
 * @date 2019年01月03日
 */
public class Base64Utils {

    private static final Logger LOG = LoggerFactory.getLogger(Base64Utils.class);

    private static final String charset = "UTF-8";


    public static String decode(String password) {
        if (StringUtils.isEmpty(password)) {
            return StringUtils.EMPTY;
        }
        String result = "";
        byte[] bytes = org.springframework.util.Base64Utils.decodeFromString(password);
        try {
            result = new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            LOG.error("base64 decode fail");
        }
        return result;
    }

    public static void main(String[] args) {
        String password = "YWJjMTIz";
        String s = null;
        try {
            s = org.springframework.util.Base64Utils.encodeToString(password.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            LOG.error("base64 decode fail");
        }
        System.out.println(decode(password));
        System.out.println(decode("abc123"));
    }
}
