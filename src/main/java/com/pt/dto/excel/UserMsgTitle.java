package com.pt.dto.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

@Data
@ContentRowHeight(20) //数据行高
@ColumnWidth(20) //列宽
public class UserMsgTitle {
    @ExcelProperty(value = "唯一编码")
    private String userCode;
    @ExcelProperty(value = "状态：0->禁用；1->正常；")
    private Integer status;
}
