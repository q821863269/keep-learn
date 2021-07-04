package cn.goduck.kl.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Desc: 角色和菜单关联表
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@ApiModel(value = "cn-goduck-kl-admin-entity-SysRoleMenu")
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu {
    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value = "菜单id")
    private Long menuId;
}