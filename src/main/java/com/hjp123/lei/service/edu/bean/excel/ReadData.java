package com.hjp123.lei.service.edu.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description: excel实体类
 * @author: Hjp
 * @time: 2020/7/24 19:10
 */

@Data
public class ReadData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
