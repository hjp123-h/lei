package com.hjp123.lei.service.edu.controller;


import com.hjp123.lei.common.code.R;
import com.hjp123.lei.service.edu.bean.vo.subject.OneSubject;
import com.hjp123.lei.service.edu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lei
 * @since 2020-07-24
 */
@Api(description="课程分类管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/edus/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;


    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addexcel")
    public R addSubject(MultipartFile file) {

        /**
         *
         *
         * @description: 上传课程分类Excel文件
         * @param file 接收的Excel文件
         * @return: com.hjp123.lei.common.code.R
         * @author: Hjp
         * @time: 2020/7/26 13:49
         */

        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息

        if (file == null) {
            return R.error().message("上传文件不能为空");
        }
        subjectService.importSubjectData(file,subjectService);
        //判断返回集合是否为空
        return R.ok();
    }

    @GetMapping("allsubject")
    public R allSubject(){

        List<OneSubject> oneSubjectList = subjectService.addSubject();

        return R.ok().data("list",oneSubjectList);
    }

}

