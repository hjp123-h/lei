package com.hjp123.lei.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjp123.lei.common.base.Exception.LeiException;
import com.hjp123.lei.service.edu.bean.EduCourse;
import com.hjp123.lei.service.edu.bean.EduCourseDescription;
import com.hjp123.lei.service.edu.bean.EduTeacher;
import com.hjp123.lei.service.edu.bean.vo.CourseInfoForm;
import com.hjp123.lei.service.edu.bean.vo.CoursePublishVo;
import com.hjp123.lei.service.edu.bean.vo.CourseQuery;
import com.hjp123.lei.service.edu.bean.vo.TeacherQuery;
import com.hjp123.lei.service.edu.mapper.EduCourseMapper;
import com.hjp123.lei.service.edu.service.EduChapterService;
import com.hjp123.lei.service.edu.service.EduCourseDescriptionService;
import com.hjp123.lei.service.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjp123.lei.service.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduVideoService videoService;
    
    @Override
    public String addCourse(CourseInfoForm courseInfoForm) {

        /**
         *
         *
         * @description: 添加课程基本信息
         * @param courseInfoForm 用户传入的课程信息vo类
         * @return: void
         * @author: Hjp
         * @time: 2020/7/27 14:04
         */

        //将用户传入的信息拷贝到数据库对应类中
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        boolean save = this.save(eduCourse);

        if (save == false){
            throw new LeiException(20001,"课程添加失败");
        }

        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm,courseDescription);
        //将课程id跟简介id同步
        String courseId = eduCourse.getId();
        courseDescription.setId(courseId);
        boolean courseD = courseDescriptionService.save(courseDescription);
        if (courseD == false){
            throw new LeiException(20001,"课程简介添加失败");
        }

        return courseId;

    }

    /**
     *
     * @description: 查询课程基本信息
     * @author: Hjp
     * @time: 2020/8/29 20:21
     */

    @Override
    public CourseInfoForm getCourseId(String courseId) {

        EduCourse course = this.getById(courseId);
        if (course == null){
            throw new LeiException(20001,"数据不存在");
        }

        CourseInfoForm courseInfoForm = new CourseInfoForm();

        BeanUtils.copyProperties(course,courseInfoForm);

        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        if (courseDescription == null){
            throw new LeiException(20001,"数据不存在");
        }

        BeanUtils.copyProperties(courseDescription,courseInfoForm);

        return courseInfoForm;
    }

    /**
     *
     * @description: 修改课程基本信息
     * @author: Hjp
     * @time: 2020/8/29 20:29
     */

    @Override
    public String updateCourse(CourseInfoForm courseInfoForm) {

        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,course);

        boolean update = this.updateById(course);
        if (!update){
            throw new LeiException(20001,"数据出错，请稍后再试");
        }

        EduCourseDescription courseDescription = new EduCourseDescription();

        BeanUtils.copyProperties(courseInfoForm,courseDescription);
        boolean update1 = courseDescriptionService.updateById(courseDescription);
        if (!update1){
            throw new LeiException(20001,"数据出错，请稍后再试");
        }

        return courseInfoForm.getId();
    }

    /**
     *
     * @description: 根据课程ID查询最终信息
     * @author: Hjp
     * @time: 2020/9/1 17:18
     */

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {

        CoursePublishVo coursePublishVo = baseMapper.selectCoursePublishVoById(id);

        return coursePublishVo;
    }

    /**
     *
     * @description: 课程条件查询分页
     * @author: Hjp
     * @time: 2020/9/1 19:58
     */

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        Integer statusInt = courseQuery.getStatusInt();

        if (statusInt != null){
            if (statusInt == 1){
                courseQuery.setStatus("Normal");
            }else {
                courseQuery.setStatus("Draft");
            }
        }

        String status = courseQuery.getStatus();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(status) ) {
            queryWrapper.eq("status", status);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor  = LeiException.class)
    public boolean removeCourseId(String courseId) {

        videoService.removeCourseId(courseId);

        chapterService.removeCourseId(courseId);

        courseDescriptionService.removeById(courseId);

        int i = baseMapper.deleteById(courseId);

        if (i == 0){
            //事务手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new LeiException(20001,"课程删除失败~");
        }

        return true;
    }
}
