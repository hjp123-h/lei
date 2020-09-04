package com.hjp123.lei.service.edu.controller.vod;

import com.hjp123.lei.common.code.R;
import com.hjp123.lei.common.vod.service.VideoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 阿里云视频服务类
 * @author: Hjp
 * @time: 2020/9/2 18:17
 */

@CrossOrigin
@RestController
@RequestMapping("/edu/vod/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    /**
     *
     * @description: 上传视频接口
     * @author: Hjp
     * @time: 2020/9/3 10:46
     */

    @PostMapping("upload/{id}")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file, @PathVariable String id) throws Exception {

        String videoId = videoService.uploadVideo(file,id);
        System.out.println(videoId);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }

    /**
     *
     * @description: 删除视频接口
     * @author: Hjp
     * @time: 2020/9/3 10:46
     */

    @DeleteMapping("remove/{id}")
    public R removeVideo(@PathVariable String id){

        boolean status = videoService.removeVideo(id);

        return R.ok();
    }

    /**
     *
     * @description: 批量删除视频接口
     * @author: Hjp
     * @time: 2020/9/3 10:46
     */

    @DeleteMapping("removes")
    public R removeVideos(@RequestParam List ids){

        boolean status = videoService.removeVideos(ids);

        return R.ok();
    }
}
