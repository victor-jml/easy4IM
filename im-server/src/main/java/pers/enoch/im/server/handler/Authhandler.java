package pers.enoch.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.generate.Auth;
import pers.enoch.im.server.session.Session;

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
    private UserService userService;

    public static final Authhandler INSTANCE = new Authhandler();

    private Authhandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Auth.AuthRequest auth) throws Exception {
        boolean isLogin = userService.checkUser(auth.getUid(), auth.getToken());
        Auth.AuthResponse response;
        if(isLogin){
            // 回执验证成功
            response = Auth.AuthResponse.newBuilder()
                    .setStatus(0)
                    .build();
            // 添加用户与对应channel关联信息
            Session.put(Long.valueOf(auth.getUid()),channelHandlerContext.channel());
        }else {
            response = Auth.AuthResponse.newBuilder()
                    .setStatus(1)
                    .setErrCode(ResultEnum.AUTH_FAILED.getCode())
                    .setErrMsg(ResultEnum.AUTH_FAILED.getMessage())
                    .build();
        }
        channelHandlerContext.channel().writeAndFlush(response);
    }
}
