package com.hjp123.lei.service.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjp123.lei.common.base.Exception.LeiException;
import com.hjp123.lei.common.code.R;
import com.hjp123.lei.service.edu.bean.EduCourse;
import com.hjp123.lei.service.edu.bean.EduTeacher;
import com.hjp123.lei.service.edu.bean.vo.CourseInfoForm;
import com.hjp123.lei.service.edu.bean.vo.CoursePublishVo;
import com.hjp123.lei.service.edu.bean.vo.CourseQuery;
import com.hjp123.lei.service.edu.bean.vo.TeacherQuery;
import com.hjp123.lei.service.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */

@Api(description="课程管理")
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    /**
     *
     * @description: 添加课程基本信息
     * @author: Hjp
     * @time: 2020/8/29 20:16
     */

    @PostMapping("addcourse")
    public R addCourse(@RequestBody CourseInfoForm courseInfoForm){

        String courseId = courseService.addCourse(courseInfoForm);

        if (!courseId.isEmpty()){
            return R.ok().data("courseId",courseId);
        }

        return R.error().message("出错了，请稍后再试");
    }

    /**
     *
     * @description: 查询课程基本信息
     * @author: Hjp
     * @time: 2020/8/29 20:16
     */

    @GetMapping("getcourse/{courseId}")
    public R getById(@PathVariable String courseId){

        CourseInfoForm courseInfoForm = courseService.getCourseId(courseId);

        if(courseInfoForm != null){
            return R.ok().data("item",courseInfoForm);
        }

        return R.error().message("出错了，请稍后再试");
    }

    /**
     *
     * @description: 修改课程基本信息
     * @author: Hjp
     * @time: 2020/8/29 20:25
     */

    @PostMapping("updatecourse")
    public R updateCourse(@RequestBody CourseInfoForm courseInfoForm){

        String courseId = courseService.updateCourse(courseInfoForm);

        if (!courseId.isEmpty()){
            return R.ok().data("courseId",courseId);
        }

        return R.error().message("出错了，请稍后再试");
    }


    /**
     *
     * @description: 根据ID查询课程最终信息
     * @author: Hjp
     * @time: 2020/9/1 17:21
     */

    @GetMapping("publishcourse/{id}")
    public R publishCourse(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);

        return R.ok().data("item",coursePublishVo);
    }

    /**
     *
     * @description: 发布课程
     * @author: Hjp
     * @time: 2020/9/1 18:44
     */
    @PostMapping("publish/{id}")
    public R publish(@PathVariable String id){
        EduCourse course = new EduCourse();
        course.setId(id);
        //设置发布状态 Draft未发布  Normal已发布
        course.setStatus("Normal");
        boolean b = courseService.updateById(course);

        return b?R.ok():R.error().message("发布出错了，请稍后再试~");
    }

    /**
     *
     * @description: 课程分类列表
     * @author: Hjp
     * @time: 2020/9/1 19:55
     */

    @PostMapping("/pageCourseCondition/{page}/{limit}")
    public R pageCourseCondition( @ApiParam(name = "page", value = "当前页码", required = true)
                                      @PathVariable Long page,

                                  @ApiParam(name = "limit", value = "每页记录数", required = true)
                                      @PathVariable Long limit,

                                  @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                                      @RequestBody CourseQuery courseQuery){
        System.out.println(courseQuery);
        Page<EduCourse> pageParam = new Page<>(page, limit);

        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);

    }

    /**
     *
     * @description: 删除课程
     * @author: Hjp
     * @time: 2020/9/2 9:10
     */

    @DeleteMapping("removecourse/{courseId}")
    public R removeCourseId(@PathVariable String courseId){

        boolean status = courseService.removeCourseId(courseId);

        return status?R.ok():R.error().message("删除失败");
    }
}

