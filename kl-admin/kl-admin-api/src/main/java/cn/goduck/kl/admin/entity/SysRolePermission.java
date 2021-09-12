package cn.goduck.kl.admin.entity;

import cn.goduck.kl.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc: 角色权限表
 * Author: Kon
 * Date: 2021-07-04 22:05
 */
@ApiModel(value="cn-goduck-kl-admin-entity-SysRolePermission")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_permission")
public class SysRolePermission extends BaseEntity {
    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色id")
    private Long roleId;

    /**
     * 资源id
     */
    @TableField(value = "permission_id")
    @ApiModelProperty(value="资源id")
    private Long permissionId;
}