package pers.enoch.im.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.mapper.user.LoginLocalMapper;
import pers.enoch.im.api.mapper.user.UserInfoMapper;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.common.constant.Constant;
import pers.enoch.im.common.model.LocalAuth;
import pers.enoch.im.common.model.Users;

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
        lambdaQueryWrapper.eq(LocalAuth::getEmail,userId);
        return loginLocalMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public LocalAuth findByEmail(String email) {
        LambdaQueryWrapper<LocalAuth> lambdaQueryWrapper = new LambdaQueryWrapper<LocalAuth>();
        lambdaQueryWrapper.eq(LocalAuth::getEmail,email);
        return loginLocalMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public LocalAuth findByPhone(String phone) {
        LambdaQueryWrapper<LocalAuth> lambdaQueryWrapper = new LambdaQueryWrapper<LocalAuth>();
        lambdaQueryWrapper.eq(LocalAuth::getPhone,phone);
        return loginLocalMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public boolean addUser(LocalAuth localAuth) {
        Users users = Users.builder()
                .userId(localAuth.getUserId())
                .name(Constant.DEFAULT_NAME)
                .status(0)
                .online(1)
                .createAt(new Date())
                .updateAt(new Date())
                .build();
        int userInsert = userInfoMapper.insert(users);
        int localInsert = loginLocalMapper.insert(localAuth);
        if(userInsert == 0 || localInsert == 0){
            log.error("用户信息插入数据库失败");
            return false;
        }
        return true;
    }
}
