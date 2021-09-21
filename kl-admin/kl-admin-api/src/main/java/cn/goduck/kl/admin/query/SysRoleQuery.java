package cn.goduck.kl.admin.query;

import cn.goduck.kl.admin.entity.SysRole;
import cn.goduck.kl.common.core.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/21 16:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色列表查询")
public class SysRoleQuery extends BasePageQuery<SysRole> {

    @ApiModelProperty(value = "角色名称")
    private String name;

}
