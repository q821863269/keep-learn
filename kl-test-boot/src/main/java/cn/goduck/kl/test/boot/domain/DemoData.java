package cn.goduck.kl.test.boot.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/19 14:49
 */
@Data
public class DemoData {

    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题1")
    private Date date1;
    @ExcelProperty(value = "日期标题2", converter = LocalDateTimeConverter.class)
    private LocalDateTime date2;
    @ExcelProperty("数字标题")
    private Double doubleData;

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;

    public static List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate1(new Date());
            data.setDate2(LocalDateTime.now());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

}
