package pers.enoch.im.common.utils;

import cn.hutool.crypto.SecureUtil;

/**
 * @Author yang.zhao
 * @Date 2020/12/18 11:03
 * @Version 1.0
 * @Description 密码工具类
 **/
public class PwdUtil {


    /**
     * 密码MD5加密
     * @param password
     * @return
     */
    public static String md5(String password){
        return SecureUtil.md5(password);
    }
}
