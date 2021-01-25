package pers.enoch.im.connector.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description: start Tcp server  & Udp Server
 **/
@Component
public class Starter implements CommandLineRunner {
    @Autowired
    private TcpServer tcpServer;

    @Override
    public void run(String... args) throws Exception {
        tcpServer.start();
    }
}
