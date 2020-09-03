package com.hjp123.lei.service.edu.controller;


import com.hjp123.lei.common.code.R;
import com.hjp123.lei.service.edu.bean.EduChapter;
import com.hjp123.lei.service.edu.bean.vo.chapter.ChapterVo;
import com.hjp123.lei.service.edu.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课时 前端控制器
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    /**
     * @param courseId 课程 ID
     * @description: 查询课程对应的章节-课时
     * @return: 统一Json返回数据
     * @author: Hjp
     * @time: 2020/8/19 23:10
     */

    @GetMapping("chapterList/{courseId}")
    public R nestedListByCourseId(@PathVariable String courseId){

        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
        return R.ok().data("items", chapterVoList);
    }

    /**
     *
     * @description: 新增章节
     * @author: Hjp
     * @time: 2020/8/30 15:36
     */

    @PostMapping("addchapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);

        return R.ok();
    }

    /**
     *
     * @description: 根据 ID 查询章节
     * @author: Hjp
     * @time: 2020/8/30 15:38
     */

    @GetMapping("{id}")
    public R chapterById(@PathVariable String id){
        EduChapter chapter = eduChapterService.getById(id);

        return R.ok().data("chapter",chapter);
    }

    /**
     *
     * @description: 修改章节
     * @author: Hjp
     * @time: 2020/8/30 15:40
     */

    @PostMapping("updatechapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        System.out.println(eduChapter);
        eduChapterService.updateById(eduChapter);

        return R.ok();
    }

    /**
     *
     * @description: 删除章节
     * @author: Hjp
     * @time: 2020/8/30 15:42
     */

    @DeleteMapping("{id}")
    public R deleteChapter(@PathVariable String id){

       Boolean flag = eduChapterService.deleteChapter(id);

       return flag?R.ok():R.error().message("删除失败");
    }
}

