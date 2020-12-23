package pers.enoch.im.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.mapper.LoginLocalMapper;
import pers.enoch.im.api.mapper.UserInfoMapper;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.common.constant.Constant;
import pers.enoch.im.api.entity.LocalAuth;
import pers.enoch.im.api.entity.Users;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author yang.zhao
 * @Date 2020/12/10 16:39
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private LoginLocalMapper loginLocalMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

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
    public boolean addUser(LocalAuth localAuth) {
        Users users = Users.builder()
                .userId(localAuth.getUserId())
                .userName(Constant.DEFAULT_NAME)
                .userStatus(0)
                .online(1)
                .createAt(new Date())
                .updateAt(new Date())
                .build();
        localAuth.setUserName(Constant.DEFAULT_NAME);
        int userInsert = userInfoMapper.insert(users);
        int localInsert = loginLocalMapper.insert(localAuth);
        if(userInsert == 0 || localInsert == 0){
            log.error("用户信息插入数据库失败");
            return false;
        }
        log.info(" {} 用户注册成功，已登录",users.getUserName());
        return true;
    }
}
