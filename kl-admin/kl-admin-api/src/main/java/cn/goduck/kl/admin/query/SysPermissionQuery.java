package cn.goduck.kl.admin.query;

import cn.goduck.kl.admin.entity.SysPermission;
import cn.goduck.kl.common.core.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/16 16:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "权限列表查询")
public class SysPermissionQuery extends BasePageQuery<SysPermission> {

    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    @ApiModelProperty(value = "权限名称")
    private String name;

}
