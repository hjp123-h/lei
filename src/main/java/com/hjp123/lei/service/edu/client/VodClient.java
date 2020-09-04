package com.hjp123.lei.service.edu.client;

import com.hjp123.lei.common.code.R;
import com.hjp123.lei.service.edu.client.impl.VodFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description: nacos调取类
 * @author: Hjp
 * @time: 2020/9/3 18:55
 * @FeignClient("service-edu") 调用服务的名字
 * @PathVariable("id") 需要加上准确的名称
 */

@FeignClient(name = "service-edu", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    /**
     *
     * @description: 从注册中心调取删除视频接口
     * @author: Hjp
     * @time: 2020/9/3 18:58
     *
     * @DeleteMapping("/edu/vod/video/remove/{id}") 需要完整的地址
     */

    @DeleteMapping("/edu/vod/video/remove/{id}")
    public R removeVideo(@PathVariable("id") String id);


    /**
     *
     * @description: 批量删除视频接口
     * @author: Hjp
     * @time: 2020/9/3 10:46
     */

    @DeleteMapping("/edu/vod/video/removes")
    public R removeVideos(@RequestParam("ids") List<String> ids);
}
