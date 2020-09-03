package com.hjp123.lei.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjp123.lei.service.edu.bean.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjp123.lei.service.edu.bean.vo.CourseInfoForm;
import com.hjp123.lei.service.edu.bean.vo.CoursePublishVo;
import com.hjp123.lei.service.edu.bean.vo.CourseQuery;
import com.hjp123.lei.service.edu.bean.vo.TeacherQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourse(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseId(String courseId);

    String updateCourse(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishVoById(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseId(String courseId);
}
