package pers.enoch.im.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Arrays;
import java.util.List;

/**
 * @Author yang.zhao
 * 2020/12/18
 * 发送邮件工具类
 **/
public class MailUtil {
    /**
     *
     */
    private static JavaMailSenderImpl javaMailSender;

    private static final SimpleMailMessage message = new SimpleMailMessage();

    /**
     * 邮件主题
     */
    private static final String SUBJECT = "EasyChat注册邮箱验证";
    /**
     * 邮件发送方
     */
    private static final String FROM = "mademyday_@outlook.com";

    @Autowired
    public MailUtil(JavaMailSenderImpl javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public static void sendMail(String receiver,String content){
        sendMail(Arrays.asList(receiver),content);
    }

    public static void sendMail(List<String> receiver,String content){
        message.setSubject(SUBJECT);
        message.setTo(receiver.toString());
        message.setText(content);
        javaMailSender.send(message);
    }
}
