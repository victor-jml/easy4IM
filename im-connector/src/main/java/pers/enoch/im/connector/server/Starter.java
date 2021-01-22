package pers.enoch.im.connector.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description: start Tcp server  & Udp Server
 **/
public class Starter implements CommandLineRunner {
    @Autowired
    private TcpServer tcpServer;
    @Autowired
    private UdpServer udpServer;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() ->{
            udpServer.start();
        });
        tcpServer.start();
    }
}
