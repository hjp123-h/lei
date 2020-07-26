package com.hjp123.lei.service.edu.service;

import com.hjp123.lei.service.edu.bean.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjp123.lei.service.edu.bean.vo.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lei
 * @since 2020-07-24
 */
public interface EduSubjectService extends IService<EduSubject> {

    void importSubjectData(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> addSubject();
}
