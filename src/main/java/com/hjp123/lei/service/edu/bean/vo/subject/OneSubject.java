package com.hjp123.lei.service.edu.bean.vo.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 课程一级分类
 * @author: Hjp
 * @time: 2020/7/26 13:47
 */

@Data
public class OneSubject {

    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();


}
