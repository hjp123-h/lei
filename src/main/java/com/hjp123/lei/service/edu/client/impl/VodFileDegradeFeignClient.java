package com.hjp123.lei.service.edu.client.impl;

import com.hjp123.lei.common.code.R;
import com.hjp123.lei.service.edu.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 熔断后执行类
 * @author: Hjp
 * @time: 2020/9/4 9:27
 */

@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R removeVideos(List<String> ids) {
        return R.error().message("删除多个视频出错了");
    }
}
