package pers.enoch.im.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.enoch.im.api.model.LocalAuth;
import pers.enoch.im.api.model.vo.req.UserPwdLoginReqVo;
import pers.enoch.im.api.model.vo.res.UserResVo;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.utils.PwdUtil;
import pers.enoch.im.common.utils.Result;
import pers.enoch.im.common.utils.TokenUtil;

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
     * @param userPwdLoginReqVo
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "byPwd")
    public Result loginByPwd(@Valid @RequestBody UserPwdLoginReqVo userPwdLoginReqVo,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Result.failure(ResultEnum.PARAM_TYPE_BIND_ERROR);
        }
        // 判断用户是用户名登录或者邮箱登录或者是手机号码登录
        LocalAuth auth = null;
        if(!Strings.isBlank(userPwdLoginReqVo.getEmail())) {
            // 判断账号密码是否正确
            auth = userService.findByEmail(userPwdLoginReqVo.getEmail());
        }else if(!Strings.isBlank(userPwdLoginReqVo.getPhone())){
            auth = userService.findByPhone(userPwdLoginReqVo.getPhone());
        }else {
            auth = userService.findById(userPwdLoginReqVo.getUserId());
        }
        // 账号不存在
        if(auth == null){
            return Result.failure(ResultEnum.USER_LOGIN_ERROR);
        }
        if(!auth.getUserPassword().equals(PwdUtil.md5(userPwdLoginReqVo.getPassword()))){
            return Result.failure(ResultEnum.USER_LOGIN_ERROR);
        }
        String userId = userPwdLoginReqVo.getUserId();
        boolean isLogin = userStatusService.checkLogin(userId);
        // 这里先实现只能单端登录,如果已登录挤掉下线
        if(isLogin){
            // 移除登录状态
            userStatusService.offline(userId);
        }
        String token = TokenUtil.makeToken();
        Long timestamp = System.currentTimeMillis();
        userStatusService.online(userId,token + "," + timestamp.toString());
        UserResVo loginResVO = UserResVo.builder()
                .token(token)
                .timestamp(timestamp)
                .build();
        return Result.success(ResultEnum.USER_LOGIN_SUCCESS,loginResVO);
    }

}
