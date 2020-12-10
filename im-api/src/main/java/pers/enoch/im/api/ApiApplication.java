package pers.enoch.im.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author yang.zhao
 * @Date 2020/12/3 15:00
 * @Version 1.0
 * @Description APi服务主启动
 **/
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }
}
