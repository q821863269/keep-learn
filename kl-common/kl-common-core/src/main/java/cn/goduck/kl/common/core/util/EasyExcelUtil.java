package cn.goduck.kl.common.core.util;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/12 11:44
 */
public class EasyExcelUtil {

    public static HorizontalCellStyleStrategy excelStyle() {
        // 表头策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景颜色设置
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont writeFont = new WriteFont();
        writeFont.setFontName("Arial");
        writeFont.setFontHeightInPoints((short) 11);
        writeFont.setBold(true);
        headWriteCellStyle.setWriteFont(writeFont);

        //内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

}
