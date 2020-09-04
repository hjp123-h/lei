package com.hjp123.lei.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjp123.lei.common.base.Exception.LeiException;
import com.hjp123.lei.service.edu.bean.EduVideo;
import com.hjp123.lei.service.edu.client.VodClient;
import com.hjp123.lei.service.edu.mapper.EduVideoMapper;
import com.hjp123.lei.service.edu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    /**
     *
     * @description: 删除小节
     * @author: Hjp
     * @time: 2020/9/2 9:26
     */

    @Override
    public void removeCourseId(String courseId) {

        //查出对应的视频id
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId)
                .select("video_source_id");

        List<EduVideo> videos = baseMapper.selectList(wrapper);
        ArrayList<String> videoIds = new ArrayList<>();
        for (EduVideo v : videos) {
            if (!StringUtils.isEmpty(v.getVideoSourceId())){
                videoIds.add(v.getVideoSourceId());
            }
        }

        System.out.println(videoIds);

        if (videoIds.size() > 0){
            vodClient.removeVideos(videoIds);
        }

        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);

        boolean remove = this.remove(videoQueryWrapper);

    }

}
