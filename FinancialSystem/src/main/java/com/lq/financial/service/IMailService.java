package com.lq.financial.service;

import java.util.Map;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-17  10:57
 * @Description: 邮件发送
 * @Version: 1.0
 */
public interface IMailService {
    void sendOnlyRemind(String[] to, String subject, Map<String,Object> map,
                        String template)throws Exception;
}
