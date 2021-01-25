package pers.enoch.im.connector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"pers.enoch.im"})
@MapperScan("pers.enoch.im.connector.mapper")
@EnableTransactionManagement
public class ConnectApp {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ConnectApp.class,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
