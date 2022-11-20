package com.lq.financial.service.impl;

import com.lq.financial.service.IMailService;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-17  10:59
 * @Description: 邮件发送工具
 * @Version: 1.0
 */
@Service
public class IMailServiceImpl implements IMailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public void sendOnlyRemind(String[] to, String subject, Map<String, Object> map, String template)
            throws Exception {
        // 1.获取模板
        Template templateTem = null;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            templateTem = freeMarkerConfigurer.getConfiguration().getTemplate(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.传入数据模型到模板，替代模板中的占位符，并将模板转化为html字符串
        String templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(templateTem, map);
        //4.设置一些必要的值
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(new InternetAddress("1907747625@qq.com", "Consultant"));
        messageHelper.setTo(to); //接收人的邮箱
        messageHelper.setSubject(subject); //标题
        messageHelper.setText(templateHtml, true); //第一个参数：模板 , 第二个参数：格式是否为html
        //5.发送邮件
        mailSender.send(message);
    }
}
