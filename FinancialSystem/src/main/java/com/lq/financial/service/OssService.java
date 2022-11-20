package com.lq.financial.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 心之所向
 * @Date2022/2/28 18:42
 * @Version V1.0
 * 描述这个类的功能
 **/
public interface OssService {
    String uploadFile(MultipartFile file) throws Exception;
}
