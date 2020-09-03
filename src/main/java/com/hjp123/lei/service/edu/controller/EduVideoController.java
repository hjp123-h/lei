package com.hjp123.lei.service.edu.controller;


import com.hjp123.lei.common.code.R;
import com.hjp123.lei.service.edu.bean.EduVideo;
import com.hjp123.lei.service.edu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
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

        boolean status = eduVideoService.removeById(id);

        return status?R.ok():R.error().message("出错了，请稍后再试~");
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

