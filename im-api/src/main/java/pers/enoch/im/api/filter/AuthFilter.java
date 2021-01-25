package pers.enoch.im.api.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import pers.enoch.im.common.constant.Constant;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.exception.IMException;
import pers.enoch.im.common.model.UserInfo;
import pers.enoch.im.common.redis.RedisService;
import pers.enoch.im.common.redis.UserInfoCache;
import pers.enoch.im.common.utils.Result;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yang.zhao
 * Date: 2021/1/25
 * Description: System token auth
 **/

@Slf4j
@Component
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String token = request.getHeader("token");
        // pass url indexOf (login || register)
        if (request.getRequestURI().indexOf("/v1/api/login") != -1
                || request.getRequestURI().indexOf("/v1/api/reg") != -1) {
        }else{
            if(Strings.isEmpty(token)){
                log.info("===========> token为空");
                Result result = new Result(ResultEnum.USER_NOT_LOGGED_IN, System.currentTimeMillis());
                response.getOutputStream().write(JSON.toJSONString(result).getBytes());
                response.getOutputStream().flush();
                response.getOutputStream().close();
                return;
            }
            if(!UserInfoCache.checkLoginByToken(token)){
                log.info("===========> token {} 过期",token);
                Result result = new Result(ResultEnum.USER_TOKEN_EXPIRE, System.currentTimeMillis());
                response.getOutputStream().write(JSON.toJSONString(result).getBytes());
                response.getOutputStream().flush();
                response.getOutputStream().close();
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
