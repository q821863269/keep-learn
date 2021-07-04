package cn.goduck.kl.common.core.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Desc: document是ES里的一个 JSON 对象，包括零个或多个field，类比关系数据库的一行记录
 * Author: Kon
 * Date: 2021/6/9 8:26
 */
@Data
@ApiModel(value = "ES基础类")
public class BaseDocument {

    @ApiModelProperty(value = "数据唯一标识")
    private String id;

    @ApiModelProperty(value = "索引名称")
    private String index;

}