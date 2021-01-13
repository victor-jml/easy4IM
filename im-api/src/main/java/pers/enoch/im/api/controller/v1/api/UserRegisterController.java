package pers.enoch.im.api.controller.v1.api;

import lombok.extern.slf4j.Slf4j;
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
import pers.enoch.im.common.utils.PwdUtil;
import pers.enoch.im.common.utils.RedisUtil;
import pers.enoch.im.common.utils.Result;
import pers.enoch.im.common.utils.TokenUtil;

import javax.validation.Valid;

/**
 * @Author yang.zhao
 * @Date 2020/12/18 11:08
 * @Version 1.0
 * @Description user reg
 **/
@Slf4j
@RestController
@RequestMapping("/v1/api/reg")
public class UserRegisterController {

    private final UserStatusService userStatusService;
    private final UserService userService;

    @Autowired
    public UserRegisterController(UserStatusService userStatusService, UserService userService){
        this.userService = userService;
        this.userStatusService = userStatusService;
    }

    @PostMapping("byUserId")
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
        String token = TokenUtil.createToken();
        if(userService.userRegister(regUser)){
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

    @RequestMapping("byPhone")
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
