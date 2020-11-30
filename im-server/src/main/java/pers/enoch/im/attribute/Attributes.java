package pers.enoch.im.attribute;

import io.netty.util.AttributeKey;
import pers.enoch.im.session.Session;

/**
 * @Author: zy
 * @Date: 2020/11/30 23:26
 * @Description:
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
