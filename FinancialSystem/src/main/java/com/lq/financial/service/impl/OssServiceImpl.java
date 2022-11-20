package com.lq.financial.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lq.financial.config.OssConfig;
import com.lq.financial.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-18  15:26
 * @Description: 阿里云OSS
 * @Version: 1.0
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        //读取工具类的数据
        String endpoint = OssConfig.END_POINT;
        String accessKeyId = OssConfig.ACCESS_KEY_ID;
        String accessKeySecret = OssConfig.ACCESS_KEY_SECRET;
        String bucketName = OssConfig.BUCKET_NAME;
        //连接oss客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流
        InputStream inputStream = file.getInputStream();
        UUID uuid = UUID.randomUUID();
        //根据时间拼接url
        String url = uuid +"/"+file.getOriginalFilename();
        //上传
        ossClient.putObject(bucketName, url, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://"+bucketName+"."+endpoint+"/"+url;
    }

}
