package com.hjp123.lei.service.edu.service;

import com.hjp123.lei.service.edu.bean.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author lei
 * @since 2020-07-27
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeCourseId(String courseId);

}
