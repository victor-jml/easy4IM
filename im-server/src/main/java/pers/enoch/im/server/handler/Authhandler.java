package pers.enoch.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.generate.Auth;

import javax.annotation.Resource;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 16:19
 * @Version 1.0
 * @Description 身份验证处理器
 **/
@Slf4j
@ChannelHandler.Sharable
public class Authhandler extends SimpleChannelInboundHandler<Auth.AuthRequest> {
    @Resource
    private UserStatusService userStatusService;

    private static class AuthHolder{
        private static final Authhandler INSTANCE = new Authhandler();
    }

    public static Authhandler getInstance(){
        return AuthHolder.INSTANCE;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Auth.AuthRequest auth) throws Exception {
        // 检验当前用户是否合法
        boolean isLegal = userStatusService.checkToken(auth.getUid(), auth.getToken());
        Auth.AuthResponse response;
        if(!isLegal){
            response = Auth.AuthResponse.newBuilder()
                    .setErrCode(ResultEnum.USER_TOKEN_EXPIRE.getCode())
                    .setErrMsg(ResultEnum.USER_TOKEN_EXPIRE.getMessage())
                    .build();
        }else {
            response = Auth.AuthResponse.newBuilder()
                    .setStatus(ResultEnum.SUCCESS.getCode())
                    .build();
        }
        channelHandlerContext.channel().writeAndFlush(response);


    }
}
