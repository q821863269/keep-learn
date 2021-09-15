package cn.goduck.kl.admin.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/15 8:50
 */
@Data
@ApiModel(value = "部门列表查询")
public class SysDeptQuery {

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "部门状态")
    private Boolean status;

}
