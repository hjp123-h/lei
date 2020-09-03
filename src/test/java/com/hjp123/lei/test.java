package com.hjp123.lei;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;


import java.util.List;

import static com.hjp123.lei.vod.initVodClient;

/**
 * @description:
 * @author: Hjp
 * @time: 2020/9/2 11:26
 */

public class test {

    public static void main(String[] args){

        //初始化客户端、请求对象和相应对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        DefaultAcsClient client = initVodClient();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        try {
            //设置视频Id
            request.setVideoId("44bbbebc0d1846088c938a0bc6251461");
            //获取请求响应
            response = client.getAcsResponse(request);

            //输出请求结果
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
