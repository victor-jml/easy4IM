package pers.enoch.im.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author yang.zhao
 * @Date 2020/12/3 15:00
 * @Version 1.0
 * @Description APi服务主启动
 **/
@SpringBootApplication(scanBasePackages = "pers.enoch.im.api.controller")
@MapperScan("pers.enoch.im.api.mapper")
@ComponentScan(value = {"pers.enoch.im.api","pers.enoch.im.common"})
@EnableCaching
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }
}
