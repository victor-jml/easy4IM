package pers.enoch.im.api;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pers.enoch.im.api.netty.starter.tcp.TcpNettyServer;

/**
 * @Author yang.zhao
 * Date: 2020/12/28
 * Description: Netty 启动
 **/
@Component
public class NettyBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            try {
                TcpNettyServer.getInstance().start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
