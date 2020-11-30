package pers.enoch.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.protocol.command.request.LoginRequestPacket;
import pers.enoch.im.protocol.command.response.LoginResponsePacket;
import pers.enoch.im.session.Session;
import pers.enoch.im.util.IDUtil;
import pers.enoch.im.util.SessionUtil;

import java.util.Date;

/**
 * @Author: zy
 * @Date: 2020/11/30 23:40
 * @Description: 登录Handler (获取登录请求)
 */
@Slf4j
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if(valid(loginRequestPacket)){
            loginResponsePacket.setSuccess(true);
            // 随机产生一个userId
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            log.info("[" + loginRequestPacket.getUserName() + "]登录成功" );
            SessionUtil.bindSession(new Session(userId,loginRequestPacket.getUserName()),channelHandlerContext.channel());
        }else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            log.info(new Date()+ "," + loginRequestPacket.getUserName() + "登录失败!");
        }

        channelHandlerContext.writeAndFlush(loginResponsePacket);
    }

    /**
     * todo 判断登录是否有效 (redis)
     * @param loginRequestPacket
     * @return
     */
    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
