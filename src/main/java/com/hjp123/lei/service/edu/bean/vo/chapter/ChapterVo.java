package com.hjp123.lei.service.edu.bean.vo.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 章节信息
 * @author: Hjp
 * @time: 2020/8/19 23:03
 */

@Data
public class ChapterVo {

    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
