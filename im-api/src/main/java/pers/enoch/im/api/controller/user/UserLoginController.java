package pers.enoch.im.api.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.api.utils.TokenUtil;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.entity.User;
import pers.enoch.im.common.utils.Result;
import pers.enoch.im.common.vo.req.UserPwdLoginReqVO;
import pers.enoch.im.common.vo.res.UserLoginResVO;

import javax.validation.Valid;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 10:46
 * @Version 1.0
 * @Description 用户登录
 **/
@Slf4j
@RestController
@RequestMapping(value = "/user/login")
public class UserLoginController  {

    private final UserStatusService userStatusService;

    private final UserService userService;

    @Autowired
    public UserLoginController(UserStatusService userStatusService,
                               UserService userService){
        this.userStatusService = userStatusService;
        this.userService = userService;
    }

    /**
     * 通过密码登录
     * todo
     * @param userPwdLoginReqVO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "byPwd")
    public Result loginByPwd(@Valid @RequestBody UserPwdLoginReqVO userPwdLoginReqVO,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Result.failure(ResultEnum.PARAM_TYPE_BIND_ERROR);
        }
        // 判断账号密码是否正确
        User user = userService.findById(userPwdLoginReqVO.getUserId());
        if(!user.getPwd().equals(userPwdLoginReqVO.getPassword())){
            return Result.failure(ResultEnum.USER_LOGIN_ERROR);
        }
        String userId = userPwdLoginReqVO.getUserId();
        boolean isLogin = userStatusService.checkLogin(userId);
        // 这里先实现只能单端登录,如果已登录挤掉下线
        if(isLogin){
            // 移除登录状态
            userStatusService.offline(userId);
        }
        String token = TokenUtil.makeToken();
        Long timestamp = System.currentTimeMillis();
        userStatusService.online(userId,token + "," + timestamp.toString());
        UserLoginResVO loginResVO = UserLoginResVO.builder()
                .userId(userId)
                .token(token)
                .timestamp(timestamp)
                .build();
        return Result.success(loginResVO);
    }

}
