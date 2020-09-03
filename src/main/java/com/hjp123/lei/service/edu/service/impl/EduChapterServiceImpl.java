package com.hjp123.lei.service.edu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjp123.lei.common.base.Exception.LeiException;
import com.hjp123.lei.service.edu.bean.EduChapter;
import com.hjp123.lei.service.edu.bean.EduCourse;
import com.hjp123.lei.service.edu.bean.EduVideo;
import com.hjp123.lei.service.edu.bean.vo.chapter.ChapterVo;
import com.hjp123.lei.service.edu.bean.vo.chapter.VideoVo;
import com.hjp123.lei.service.edu.mapper.EduChapterMapper;
import com.hjp123.lei.service.edu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjp123.lei.service.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * @param courseId 课程 ID
     * @description: 查询课程大纲
     * @return: 返回封装后的课程-章节-课时
     * @author: Hjp
     * @time: 2020/8/19 23:08
     */
    @Override
    public List<ChapterVo> nestedList(String courseId) {

        //创建返回对象
        ArrayList<ChapterVo> list = new ArrayList<>();

        //查询课程对应的章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> chapters = this.list(chapterQueryWrapper);

        //查询课程对应的课时
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> videos = eduVideoService.list(videoQueryWrapper);

        for (EduChapter chapter : chapters) {
            //创建章节对象
            ChapterVo chapterVo = new ChapterVo();
            //将源章节对象拷贝到vo对象中
            BeanUtils.copyProperties(chapter,chapterVo);

            //创建课时迭代对象
            Iterator<EduVideo> iterator = videos.iterator();
            while (iterator.hasNext()){
                //遍历获得源课时对象
                EduVideo video = iterator.next();
                //创建新课时vo对象
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video,videoVo);

                //判断课时id是否跟课程id相同
                if (video.getChapterId().equals(chapterVo.getId())){

                    List<VideoVo> children = chapterVo.getChildren();
                    children.add(videoVo);

                    iterator.remove();
                }
            }

            list.add(chapterVo);
        }

        return list;
    }

    @Override
    public Boolean deleteChapter(String id) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        int count = eduVideoService.count(wrapper);

        if (count > 0){
            throw new LeiException(20001,"出错了，请稍后再试");
        }else {
            int i = baseMapper.deleteById(id);

            return i > 0;
        }
    }

    /**
     *
     * @description: 删除章节
     * @author: Hjp
     * @time: 2020/9/2 9:29
     */

    @Override
    public void removeCourseId(String courseId) {

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);

        this.remove(wrapper);
    }
}
