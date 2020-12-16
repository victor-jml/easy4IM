package pers.enoch.im.api.service;

/**
 * 用户登录验证
 * @Author yang.zhao
 */
public interface UserService {
    /**
     * 通过userId查询用户
     * @param uid
     * @return User
     */
    User findById(String uid);
}
