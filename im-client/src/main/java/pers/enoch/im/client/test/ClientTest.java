package pers.enoch.im.client.test;

import pers.enoch.im.client.starter.tcp.NettyClient;
import pers.enoch.im.common.protobuf.Auth;
import pers.enoch.im.common.protobuf.Single;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author yang.zhao
 * Date: 2020/12/28
 * Description:
 **/
public class ClientTest {
    public static void main(String[] args) {
        new Thread(()->{
            NettyClient client = new NettyClient();
            Scanner sc = new Scanner(System.in);
            final AtomicInteger login = new AtomicInteger(0);
            final String[] userId = new String[1];
            while (!Thread.interrupted()){
                if(login.get() == 0){
                    System.out.println("输入账号:");
                    userId[0] = sc.nextLine();
                    System.out.println("输入token:");
                    String token = sc.nextLine();
                    Auth.AuthRequest authRequest = Auth.AuthRequest.newBuilder()
                            .setUid(userId[0])
                            .setToken(token)
                            .build();
                    client.send(authRequest);
                    login.getAndIncrement();
                }else {
                    System.out.println("输入发送者id:");
                    String to = sc.nextLine();
                    System.out.println("输入发送内容");
                    String content = sc.nextLine();
                    Single.SingleSendRequest singleSendRequest = Single.SingleSendRequest.newBuilder()
                            .setFrom(userId[0])
                            .setTo(to)
                            .setContent(content)
                            .build();
                    client.send(singleSendRequest);
                }
            }
        }).start();

    }

}
