package pers.enoch.im.api.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.utils.ResultUtil;
import pers.enoch.im.common.vo.req.UserPwdLoginReqVO;
import pers.enoch.im.common.vo.res.BaseResVO;

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

    /**
     * 通过密码登录
     * todo
     * @param userPwdLoginReqVO
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "byPwd")
    public BaseResVO loginByPwd(@Valid @RequestBody UserPwdLoginReqVO userPwdLoginReqVO,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(null);
    }
}
