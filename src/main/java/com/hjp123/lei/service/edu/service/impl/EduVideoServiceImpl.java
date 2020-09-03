package com.hjp123.lei.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjp123.lei.common.base.Exception.LeiException;
import com.hjp123.lei.service.edu.bean.EduVideo;
import com.hjp123.lei.service.edu.mapper.EduVideoMapper;
import com.hjp123.lei.service.edu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     *
     * @description: 删除小节
     * @author: Hjp
     * @time: 2020/9/2 9:26
     */

    @Override
    public void removeCourseId(String courseId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);

        boolean remove = this.remove(videoQueryWrapper);

    }

}
