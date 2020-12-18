package pers.enoch.im.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author yang.zhao
 * 2020/12/18
 * 电话号码后台验证
 **/
public class PhoneUtil {

    public static boolean isPhone(String phone) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // 手机号码正则匹配
        String valid="^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
        if(StringUtils.isNotBlank(phone)){
            p = Pattern.compile(valid);
            m = p.matcher(phone);
            b = m.matches();
        }
        return b;
    }

    public static int createCode(){
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

}
