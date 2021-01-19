package pers.enoch.im.api.controller.v1.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.enoch.im.api.model.vo.req.UserLoginByPwdReqVo;
import pers.enoch.im.api.model.vo.res.UserResVo;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.utils.Result;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 10:46
 * @Version 1.0
 * @Description 用户登录
 **/
@Slf4j
@RestController
@RequestMapping(value = "/v1/api/login")
public class UserLoginController  {

    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService){
        this.userService = userService;
    }

    /**
     * user login by sms code
     * @param userLoginBySmsReqVo
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "bySms")
    public Result loginBySms(@Valid @RequestBody UserLoginBySmsReqVo userLoginBySmsReqVo,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Result.failure(ResultEnum.PARAM_TYPE_BIND_ERROR);
        }
        Optional<UserResVo> response = Optional.ofNullable(userService.login(userLoginBySmsReqVo));
        return response.map(userResVo -> Result.success(ResultEnum.USER_LOGIN_SUCCESS, userResVo))
                .orElseGet(() -> Result.success(ResultEnum.USER_LOGIN_ERROR, null));
    }

    /**
     * user login by pwd
     * @param userLoginByPwdReqVo
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "byPwd")
    public Result loginByPwd(@Valid @RequestBody UserLoginByPwdReqVo userLoginByPwdReqVo,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Result.failure(ResultEnum.PARAM_TYPE_BIND_ERROR);
        }
        Optional<UserResVo> response = Optional.ofNullable(userService.login(userLoginByPwdReqVo));
        return Result.success(response.isPresent() ? ResultEnum.USER_LOGIN_SUCCESS : ResultEnum.USER_LOGIN_ERROR ,
                response.isPresent() ? response.get() : System.currentTimeMillis());
    }

}
