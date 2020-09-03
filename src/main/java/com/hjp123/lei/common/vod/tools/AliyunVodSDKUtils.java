package com.hjp123.lei.common.vod.tools;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;

/**
 * @description: 阿里云视频点播初始化工具类
 * @author: Hjp
 * @time: 2020/9/2 19:26
 */


public class AliyunVodSDKUtils {

    public static DefaultAcsClient initVodClient(String accessKeyId,String accessKeySecret) throws ClientException {

        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
