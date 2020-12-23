package pers.enoch.im.api.service;

import pers.enoch.im.api.entity.LocalAuth;

/**
 * 用户登录验证
 * @Author yang.zhao
 */
public interface UserService {

    /**
     * 通过用户id查找账号
     * @param userId
     * @return LocalAuth （包含密码验证的实体）
     */
    LocalAuth findById(String userId);

    /**
     * 通过email查找账号
     * @param email
     * @return LocalAuth （包含密码验证的实体）
     */
    LocalAuth findByEmail(String email);

    /**
     * 通过电话号码查找账号
     * @param phone
     * @return LocalAuth （包含密码验证的实体）
     */
    LocalAuth findByPhone(String phone);

    /**
     * 通过注册添加用户
     * @param localAuth
     * @return
     */
    boolean addUser(LocalAuth localAuth);
}
