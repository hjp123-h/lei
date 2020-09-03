package com.hjp123.lei.service.edu.service;

import com.hjp123.lei.service.edu.bean.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjp123.lei.service.edu.bean.vo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> nestedList(String courseId);

    Boolean deleteChapter(String id);

    void removeCourseId(String courseId);
}
