package pers.enoch.im.api.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.api.utils.TokenUtil;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.entity.User;
import pers.enoch.im.common.utils.ResultUtil;
import pers.enoch.im.common.vo.req.UserPwdLoginReqVO;
import pers.enoch.im.common.vo.res.BaseResVO;
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
    public BaseResVO loginByPwd(@Valid @RequestBody UserPwdLoginReqVO userPwdLoginReqVO,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
        // 判断账号密码是否正确
        User user = userService.findById(userPwdLoginReqVO.getUserId());
        if(!user.getPwd().equals(userPwdLoginReqVO.getPassword())){
            return ResultUtil.error(ResultEnum.PARAM_VERIFY_FALL,"账号或密码验证错误");
        }
        String userId = userPwdLoginReqVO.getUserId();
        boolean isLogin = userStatusService.checkLogin(userId.toString());
        // 这里先实现只能单端登录,如果已登录挤掉下线
        if(isLogin){
            // 移除登录状态
            userStatusService.offline(userId);
        }
        String token = TokenUtil.makeToken();
        userStatusService.online(userId,token);
        UserLoginResVO loginResVO = UserLoginResVO.builder()
                .userId(userId)
                .token(token)
                .build();
        return ResultUtil.success(loginResVO);
    }
}
