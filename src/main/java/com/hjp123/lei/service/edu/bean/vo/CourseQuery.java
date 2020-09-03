package com.hjp123.lei.service.edu.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 课程条件查询
 * @author: Hjp
 * @time: 2020/9/1 19:54
 */

@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "是否发布")
    private Integer statusInt;

    @ApiModelProperty(value = "是否发布")
    private String status;
}
