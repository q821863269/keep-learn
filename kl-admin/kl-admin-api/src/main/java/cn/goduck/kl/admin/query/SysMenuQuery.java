package cn.goduck.kl.admin.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/16 14:19
 */
@Data
@ApiModel(value = "菜单列表查询")
public class SysMenuQuery {

    @ApiModelProperty(value = "菜单名称")
    private String name;

}
