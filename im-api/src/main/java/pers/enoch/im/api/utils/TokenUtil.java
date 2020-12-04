package pers.enoch.im.api.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 15:11
 * @Version 1.0
 * @Description 生成token以及检验token是否生效
 **/
public class TokenUtil {
    /**
     * 生成Token
     * @return
     */
    public static String makeToken() {
        return IdUtil.simpleUUID();
    }
}
