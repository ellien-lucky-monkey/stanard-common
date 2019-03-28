package com.standard.common.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

/**
 * @author Jiangkui
 * @date 2019年01月03日
 */
public class AesUtils {
    public static final String ENCODING = "utf-8";

    public static String encode(String clearText, String salt) {
        return encode(clearText, salt, ENCODING);
    }

    public static String encode(String clearText, String salt, String encoding) {
        byte[] cipherText = encrypt(clearText, salt, encoding);
        assert cipherText != null;
        return parseByte2HexStr(cipherText);
    }

    public static byte[] encrypt(String clearText, String salt) {
        return encrypt(clearText, salt, ENCODING);
    }

    public static byte[] encrypt(String clearText, String salt, String encoding) {
        try {
            Cipher cipher = createCipher(salt, Cipher.ENCRYPT_MODE, encoding);
            byte[] byteContent = clearText.getBytes(encoding);
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // 加密
    }

    public static Cipher createCipher(String salt, int mode, String encoding) {
        try {

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(salt.getBytes(encoding));

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, random);

            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(mode, key);// 初始化
            return cipher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] cipherText, String salt, String encoding) {
        try {
            Cipher cipher = createCipher(salt, Cipher.DECRYPT_MODE, encoding);// 创建密码器
            byte[] result = cipher.doFinal(cipherText);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String decode(String cipherText, String salt) {
        return decode(cipherText, salt, ENCODING);
    }

    public static String decode(String cipherText, String salt, String encoding) {
        byte[] clearText = decrypt(parseHexStr2Byte(cipherText), salt, encoding);
        try {
            return new String(clearText, ENCODING); // 加密
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
