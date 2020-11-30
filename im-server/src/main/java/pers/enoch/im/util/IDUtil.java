package pers.enoch.im.util;

import java.util.UUID;

/**
 * @Author: zy
 * @Date: 2020/12/1 00:05
 * @Description:
 */
public class IDUtil {
    public static String randomId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
