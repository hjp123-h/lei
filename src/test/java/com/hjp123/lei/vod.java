package com.hjp123.lei;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;

/**
 * @description:
 * @author: Hjp
 * @time: 2020/9/2 11:22
 */

public class vod {

    private static String accessKeyId = "LTAI4FrLFYKADDNuceHGZSw2";

    private static String  accessKeySecret = "IJdY7lEwuhj63I31VZWapP0EQCDJuK";

    public static DefaultAcsClient initVodClient() throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

}
