package pers.enoch.im.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.enoch.im.common.utils.QCloudCosUtils;

/**
 * @Author: zy
 * @Date: 2021/1/16 22:37
 * @Description:
 */
@Configuration
public class QCloudCosUtilsConfig {
    @ConfigurationProperties(prefix = "spring.qcloud")
    @Bean
    public QCloudCosUtils qcloudCosUtils() {
        return new QCloudCosUtils();
    }
}