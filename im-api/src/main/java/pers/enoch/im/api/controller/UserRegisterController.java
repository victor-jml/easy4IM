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
import pers.enoch.im.api.model.vo.req.UserRegisterReqVo;
import pers.enoch.im.api.model.vo.res.UserResVo;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.constant.Constant;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.utils.*;

import javax.validation.Valid;

/**
 * @Author yang.zhao
 * @Date 2020/12/18 11:08
 * @Version 1.0
 * @Description 用户注册
 **/
@Slf4j
@RestController
@RequestMapping("/user/reg")
public class UserRegisterController {

    private final UserStatusService userStatusService;
    private final UserService userService;

    @Autowired
    public UserRegisterController(UserStatusService userStatusService,UserService userService){
        this.userService = userService;
        this.userStatusService = userStatusService;
    }

    @PostMapping("sendCode")
    public Result sendCode(String phone){
        log.info(" {} 新用户注册",phone);
        if(Strings.isBlank(phone)){
            return Result.failure(ResultEnum.PARAM_IS_BLANK);
        }
        if(!PhoneUtil.isPhone(phone)){
            return Result.failure(ResultEnum.PHONE_VALID_ERROR);
        }
        // 生成code并存入redis中 过期时间为5min
        int code = PhoneUtil.createCode();
        RedisUtil.set(Constant.REDIS_PHONE_PREFIX,phone,String.valueOf(code),3000L);
        return Result.success();
    }

    @PostMapping("regByUserId")
    public Result regByUserId(@Valid @RequestBody UserRegisterReqVo userRegisterReqVo,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.failure(ResultEnum.PARAM_NOT_COMPLETE);
        }
        LocalAuth user = userService.findById(userRegisterReqVo.getUserId());
        if(user != null){
            return Result.failure(ResultEnum.USER_HAS_EXISTED);
        }
        // 成功注册并登录直接返回token
        LocalAuth regUser = LocalAuth.builder()
                .userId(userRegisterReqVo.getUserId())
                .userPassword(PwdUtil.md5(userRegisterReqVo.getPassword()))
                .build();
        String token = TokenUtil.makeToken();
        if(userService.addUser(regUser)){
            userStatusService.online(userRegisterReqVo.getUserId(),token);
        }else {
            return Result.failure(ResultEnum.SERVER_ERROR);
        }
        UserResVo regResVO = UserResVo.builder()
                .token(token)
                .timestamp(System.currentTimeMillis())
                .build();
        return Result.success(ResultEnum.USER_REGISTE_SUCCESS,regResVO);

    }

    @RequestMapping("regByPhone")
    public Result regByPhone(@Valid @RequestBody UserRegisterReqVo userRegisterReqVo,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.failure(ResultEnum.PARAM_NOT_COMPLETE);
        }
        Object o = RedisUtil.get(Constant.REDIS_PHONE_PREFIX, userRegisterReqVo.getPhone());
        if(o == null){
            return Result.failure(ResultEnum.CODE_VALID_EXPIRED);
        }
        String code = (String)o;
        if(!code.equals(userRegisterReqVo.getCode())){
            return Result.failure(ResultEnum.CODE_VALID_ERROR);
        }
        // 逻辑到这里表示验证码正确
        // 注册逻辑(注册成功自动登录)
        // todo 待申请短信服务后实现短信注册登录
        return Result.success();
    }

}
