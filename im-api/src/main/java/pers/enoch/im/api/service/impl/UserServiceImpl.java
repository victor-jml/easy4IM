package pers.enoch.im.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.service.UserService;

/**
 * @Author yang.zhao
 * @Date 2020/12/10 16:39
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(String uid) {
        // todo 查询数据库
        return User.builder()
                .uid(Long.valueOf(uid))
                .name("zy")
                .pwd("123")
                .build();
    }
}
