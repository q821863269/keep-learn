package cn.goduck.kl.common.core.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Desc: 基础分页类
 * Author: Kon
 * Date: 2021/6/11 7:35
 */
@Data
@ApiModel(value = "基础分页类")
public class BasePageQuery<T> {

    @ApiModelProperty(value = "当前页", example = "1")
    private int pageNum = 1;

    @ApiModelProperty(value = "每页记录数", example = "10")
    private int pageSize = 10;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 获取分页参数
     *
     * @return 分页参数
     */
    public Page<T> page() {
        return new Page<>(pageNum, pageSize);
    }

    /**
     * 最多导出1W条
     *
     * @return 分页参数
     */
    public Page<T> exportPage() {
        Page<T> page = new Page<>(1, 10000);
        page.setSearchCount(false);
        return page;
    }

    /**
     * 导出模板
     *
     * @return true：是   false：否
     */
    public boolean exportEmptyExcel() {
        return pageNum == 0;
    }

}