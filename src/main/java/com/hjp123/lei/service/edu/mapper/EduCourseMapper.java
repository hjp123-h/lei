package com.hjp123.lei.service.edu.mapper;

import com.hjp123.lei.service.edu.bean.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjp123.lei.service.edu.bean.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo selectCoursePublishVoById(String id);
}
