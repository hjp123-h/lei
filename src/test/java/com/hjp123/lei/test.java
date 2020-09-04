package com.hjp123.lei;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static com.hjp123.lei.vod.initVodClient;

/**
 * @description:
 * @author: Hjp
 * @time: 2020/9/2 11:26
 */

public class test {

    public static void main(String[] args){

        List<String> objects = new ArrayList<>();
        objects.add("123");
        objects.add("456");
        String join = String.join(",", objects);
        System.out.println(join);

    }
}
