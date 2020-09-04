package com.hjp123.lei.service.edu.controller;


import com.hjp123.lei.common.base.Exception.LeiException;
import com.hjp123.lei.common.code.R;
import com.hjp123.lei.service.edu.bean.EduVideo;
import com.hjp123.lei.service.edu.client.VodClient;
import com.hjp123.lei.service.edu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程小节 前端控制器
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    /**
     *
     * @description: 添加小节
     * @author: Hjp
     * @time: 2020/9/1 9:14
     */  
    
    @PostMapping("addvideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        
        eduVideoService.save(eduVideo);
        
        return R.ok();
    }

    /**
     *
     * @description: 删除小节
     * @author: Hjp
     * @time: 2020/9/1 9:22
     */

    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){

        EduVideo video = eduVideoService.getById(id);
        if (video != null && !StringUtils.isEmpty(video.getVideoSourceId())){

            //采用nacos远程调用vod中的方法
            R r = vodClient.removeVideo(video.getVideoSourceId());
            if (r.getCode() == 20001){
                throw new LeiException(20001,"服务熔断了");
            }
            boolean status = eduVideoService.removeById(id);
            return status?R.ok():R.error().message("出错了，请稍后再试~");
        }
        return R.error().message("小节不存在~");
    }
    
    /**
     *
     * @description: 查询小节
     * @author: Hjp
     * @time: 2020/9/1 9:17
     */

    @GetMapping("getVideo/{id}")
    public R getVideo(@PathVariable String id){

        EduVideo video = eduVideoService.getById(id);


        return R.ok().data("video",video);
    }

    /**
     *
     * @description: 修改小节
     * @author: Hjp
     * @time: 2020/9/1 10:13
     */

    @PutMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo video){

        eduVideoService.updateById(video);

        return R.ok();
    }
}

