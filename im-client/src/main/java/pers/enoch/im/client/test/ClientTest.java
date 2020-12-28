package pers.enoch.im.client.test;

import pers.enoch.im.client.starter.tcp.NettyClient;
import pers.enoch.im.common.protobuf.Auth;
import pers.enoch.im.common.protobuf.Single;

import java.util.Scanner;

/**
 * @Author yang.zhao
 * Date: 2020/12/28
 * Description:
 **/
public class ClientTest {
    public static void main(String[] args) {
        NettyClient client = NettyClient.getInstance();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号：");
        String name = sc.next();
        System.out.println("请输入token：");
        String token = sc.next();
        Auth.AuthRequest request = Auth.AuthRequest.newBuilder()
                .setUid(name)
                .setToken(token)
                .build();
        client.send(request);
        new Thread(()->{
            while (!Thread.interrupted()){
                System.out.println("输入发送的消息：");
                Scanner scanner = new Scanner(System.in);
                String words = scanner.nextLine();
                System.out.println("请输入接受者: ");
                String receive = scanner.nextLine();
                Single.SingleSendRequest chatRequest = Single.SingleSendRequest.newBuilder()
                        .setFrom(name)
                        .setTo(receive)
                        .setContent(words)
                        .build();
                client.send(request);
            }
        });
    }

}
