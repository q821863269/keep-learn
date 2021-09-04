package cn.goduck.kl.common.core.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    /**
     * 获取分页参数
     *
     * @return 分页参数
     */
    public Page<T> page() {
        return new Page<>(pageNum, pageSize);
    }

}