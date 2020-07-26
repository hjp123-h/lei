package com.hjp123.lei.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjp123.lei.service.edu.bean.vo.subject.OneSubject;
import com.hjp123.lei.service.edu.bean.vo.subject.TwoSubject;
import com.hjp123.lei.service.edu.listener.SubjectExcelListener;
import com.hjp123.lei.service.edu.bean.EduSubject;
import com.hjp123.lei.service.edu.bean.excel.ReadData;
import com.hjp123.lei.service.edu.mapper.EduSubjectMapper;
import com.hjp123.lei.service.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lei
 * @since 2020-07-24
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService subjectService) {

        /**
         *
         *
         * @description: 上传课程信息
         * @param file 用户上传的Excel文件
         * @param subjectService 传入SubjectExcelListener中的Service（需要手动传入）
         * @return: void
         * @author: Hjp
         * @time: 2020/7/26 13:52
         */

        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ReadData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }

    @Override
    public List<OneSubject> addSubject() {

        /**
         *
         * @description: 获取所有课程分类信息
         * @author: Hjp
         * @time: 2020/7/26 13:52
         */

        //创建分类返回列表
        ArrayList<OneSubject> subjectArrayList = new ArrayList<>();

        //获取所有一级分类
        QueryWrapper<EduSubject> oneSubjectQueryWrapper = new QueryWrapper<>();
        oneSubjectQueryWrapper.eq("parent_id",0);
        List<EduSubject> oneSubjects = this.list(oneSubjectQueryWrapper);

        //获取所有二级分类
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("parent_id",0);
        List<EduSubject> twoSubjects = this.list(queryWrapper);

        //遍历一级分类
        for (EduSubject s : oneSubjects) {
            //创建一级分类对象
            OneSubject oneSubject = new OneSubject();
            //将数据库表中属性拷贝到一级分类对象中
            BeanUtils.copyProperties(s,oneSubject);

            //创建二级分类迭代器
            Iterator<EduSubject> iterator = twoSubjects.iterator();
            while (iterator.hasNext()){
                //创建二级分类对象
                TwoSubject twoSubject = new TwoSubject();
                //获取数据库二级分类对象
                EduSubject next = iterator.next();
                //将数据库二级分类拷贝到二级分类对象中
                BeanUtils.copyProperties(next,twoSubject);

                //判断数据库中父id，是否跟当前一级分类id相同
                if (next.getParentId().equals(oneSubject.getId())){
                    //获取一级分类中children列表
                    List<TwoSubject> children = oneSubject.getChildren();
                    //将二级分类对象传入
                    children.add(twoSubject);
                    //删除当前对象 减少下次迭代时间
                    iterator.remove();
                }
            }
            //将当前循环中一级分类放入集合
            subjectArrayList.add(oneSubject);
        }


        return subjectArrayList;
    }
}
