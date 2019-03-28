package com.standard.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Jiangkui
 * @date 2018年12月26日
 */
public class DateUtils {
    public static void main(String[] args) {
        Long expire = 24 * 3600 * 1000L;
        System.out.println("args = [" + expire + "]");
        Date date = new Date(expire);
        System.out.println("args = [" + expire + "]");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println("args = [" + args + "]");
    }


    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }
}

