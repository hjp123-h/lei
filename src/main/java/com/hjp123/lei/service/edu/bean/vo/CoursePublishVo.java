package com.hjp123.lei.service.edu.bean.vo;

import lombok.Data;

/**
 * @description: 课程信息最终展示类
 * @author: Hjp
 * @time: 2020/9/1 17:01
 */

@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
