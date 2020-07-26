package com.hjp123.lei.common.oos.seriver;import com.aliyun.oss.OSS;import com.aliyun.oss.OSSClientBuilder;import org.joda.time.DateTime;import org.springframework.beans.factory.annotation.Value;import org.springframework.stereotype.Service;import org.springframework.web.multipart.MultipartFile;import java.io.IOException;import java.io.InputStream;import java.net.URL;import java.util.Date;import java.util.UUID;/** * @description: 上传头像的实现类 * @author: Hjp * @time: 2020/7/22 15:10 */@Servicepublic class FileService {    @Value("${aliyun.oss.file.endpoint}")    private String endPoint;    @Value("${aliyun.oss.file.keyid}")    private String accessKeyId;    @Value("${aliyun.oss.file.keysecret}")    private String  accessKeySecret;    @Value("${aliyun.oss.file.bucketname}")    private String bucketName;    public String uploadAvatar(MultipartFile file) {        /**         *         *         * @description: 上传图片到阿里云oos         * @param file 图片         * @return: java.lang.String         * @author: Hjp         * @time: 2020/7/23 14:10         */        // 创建OSSClient实例。        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);        try {            InputStream inputStream = file.getInputStream();            // 上传文件流。            String filename =new DateTime().toString("yyyy-MM-dd~HH.mm ") + String.valueOf(UUID.randomUUID()+".jpg");            ossClient.putObject(bucketName, filename, inputStream);            // 设置URL过期时间为360天。            Date expiration = new Date(System.currentTimeMillis() + 3600 * 24 * 360);            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。            URL url = ossClient.generatePresignedUrl(bucketName, filename, expiration);            // 关闭OSSClient。            ossClient.shutdown();            return String.valueOf(url);        } catch (IOException e) {            e.printStackTrace();        }        return null;    }}