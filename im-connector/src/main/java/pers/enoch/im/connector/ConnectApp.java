package pers.enoch.im.connector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"pers.enoch.im"})
public class ConnectApp {
    public static void main(String[] args) {
        SpringApplication.run(ConnectApp.class,args);
    }
}
