package pers.enoch.im.api.service;


import pers.enoch.im.common.model.LocalAuth;
import pers.enoch.im.common.model.vo.req.UserLoginByPwdReqVo;
import pers.enoch.im.common.model.vo.req.UserRegisterReqVo;
import pers.enoch.im.common.model.vo.res.UserResVo;

/**
 * 用户登录
 * @Author yang.zhao
 */
public interface UserService {

    /**
     * user login by pwd
     * @param userLoginByPwdReqVo userLogin info {userId or Email, password}
     * @return UserResVo
     */
    UserResVo login(UserLoginByPwdReqVo userLoginByPwdReqVo);


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
     * user register by
     * @param userRegisterReqVo user register by UserInfo
     * @return
     */
    UserResVo userRegister(UserRegisterReqVo userRegisterReqVo);
}
