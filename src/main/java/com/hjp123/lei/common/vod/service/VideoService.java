package com.hjp123.lei.common.vod.service;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjp123.lei.common.base.Exception.LeiException;
import com.hjp123.lei.common.oos.bean.ConstantPropertiesUtil;
import com.hjp123.lei.common.vod.tools.AliyunVodSDKUtils;
import com.hjp123.lei.service.edu.bean.EduVideo;
import com.hjp123.lei.service.edu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 阿里云视频操作类
 * @author: Hjp
 * @time: 2020/9/2 18:18
 */

@Service
public class VideoService {

    @Value("${aliyun.oss.file.keyid}")
    private String accessKeyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String  accessKeySecret;

    @Autowired
    private EduVideoService videoService;

    public String uploadVideo(MultipartFile file,String id) {

        try {
            //文件流
            InputStream inputStream = file.getInputStream();
            //视频源名称
            String originalFilename = file.getOriginalFilename();
            //视频上传后名称
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    accessKeyId, accessKeySecret,title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();

            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                if(StringUtils.isEmpty(videoId)){
                    throw new LeiException(20001, errorMessage);
                }
            }
            return videoId;
        } catch (IOException e) {
            throw new LeiException(20001, "vod 服务上传失败");
        }
    }

    /**
     *
     * @description: 删除阿里云视频
     * @author: Hjp
     * @time: 2020/9/3 10:46
     */

    public boolean removeVideo(String id) {

        try {
            //初始化
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId,accessKeySecret);

            //创建删除对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //设置删除id
            request.setVideoIds(id);

            //发送获取回调数据
            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");

            return true;
        } catch (ClientException e) {
            e.printStackTrace();

            throw new LeiException(20001,"删除失败，请稍后再试");
        }

    }
}
