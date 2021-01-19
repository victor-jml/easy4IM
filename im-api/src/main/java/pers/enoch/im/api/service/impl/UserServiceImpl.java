package pers.enoch.im.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.mapper.LoginLocalMapper;
import pers.enoch.im.api.mapper.UserInfoMapper;
import pers.enoch.im.api.model.LocalAuth;
import pers.enoch.im.api.model.UserInfo;
import pers.enoch.im.api.model.vo.req.UserLoginByPwdReqVo;
import pers.enoch.im.api.model.vo.req.UserRegisterReqVo;
import pers.enoch.im.api.model.vo.res.UserResVo;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.utils.PwdUtil;
import pers.enoch.im.common.utils.TokenUtil;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author yang.zhao
 * @Date 2020/12/10 16:39
 * @Version 1.0
 * @Description todo (complete send sms code  &  valid sms code )
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserStatusService userStatusService;

    @Resource
    private LoginLocalMapper loginLocalMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserResVo login(UserLoginByPwdReqVo userLoginByPwdReqVo) {
        UserResVo result = UserResVo.builder().build();
        LocalAuth userAuth = loginByPwdType(userLoginByPwdReqVo);
        if(userAuth == null){
            return null;
        }
        if(!userAuth.getUserPassword().equals(PwdUtil.md5(userLoginByPwdReqVo.getPassword()))){
            // if password wrong
            return null;
        }
        // if user online ,then kick it out
        if(userStatusService.checkLogin(userAuth.getUserId())){
            userStatusService.offline(userAuth.getUserId());
        }
        UserInfo userInfo = findUserInfo(userAuth.getUserId());
        BeanUtils.copyProperties(result,userInfo);
        String token = TokenUtil.createToken();
        long timestamp = System.currentTimeMillis();
        userStatusService.online(userAuth.getUserId(),token + "," + Long.toString(timestamp));
        result.setToken(token);
        result.setTimestamp(timestamp);
        return result;
    }



    /**
     * select user base info by userId
     * @param userId userId
     * @return Users
     */
    private UserInfo findUserInfo(String userId){
        return userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_id",userId));
    }

    /**
     * check user login by password type(Email or UserId)
     * @param userLoginByPwdReqVo userLogin info {userId or Email, password}
     * @return
     */
    private LocalAuth loginByPwdType(UserLoginByPwdReqVo userLoginByPwdReqVo){
        if(userLoginByPwdReqVo.getUserId() !=  null){
            log.info("user id :{} login at {}",userLoginByPwdReqVo.getUserId(),new Date());
            return findById(userLoginByPwdReqVo.getUserId());
        }else if(userLoginByPwdReqVo.getEmail() != null){
            log.info("user email : {} login at {}",userLoginByPwdReqVo.getEmail(),new Date());
            return findByEmail(userLoginByPwdReqVo.getEmail());
        }else{
            log.error("login error , {}", ResultEnum.PARAM_NOT_COMPLETE.getMessage());
            return null;
        }
    }

    @Override
    public LocalAuth findById(String userId) {
        LambdaQueryWrapper<LocalAuth> lambdaQueryWrapper = new LambdaQueryWrapper<LocalAuth>();
        lambdaQueryWrapper.eq(LocalAuth::getUserId,userId);
        return loginLocalMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public LocalAuth findByEmail(String email) {
        LambdaQueryWrapper<LocalAuth> lambdaQueryWrapper = new LambdaQueryWrapper<LocalAuth>();
        lambdaQueryWrapper.eq(LocalAuth::getUserEmail,email);
        return loginLocalMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public LocalAuth findByPhone(String phone) {
        LambdaQueryWrapper<LocalAuth> lambdaQueryWrapper = new LambdaQueryWrapper<LocalAuth>();
        lambdaQueryWrapper.eq(LocalAuth::getUserPhone,phone);
        return loginLocalMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public UserResVo userRegister(UserRegisterReqVo userRegisterReqVo) {
        return null;
    }

}
