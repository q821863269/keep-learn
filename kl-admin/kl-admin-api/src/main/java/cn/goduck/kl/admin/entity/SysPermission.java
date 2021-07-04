package cn.goduck.kl.admin.entity;

import cn.goduck.kl.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc: 权限表
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@ApiModel(value = "cn-goduck-kl-admin-entity-SysPermission")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_permission")
public class SysPermission extends BaseEntity {

    /**
     * 权限名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 菜单模块id
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value = "菜单模块id")
    private Long menuId;

    /**
     * URL权限标识
     */
    @TableField(value = "url_perm")
    @ApiModelProperty(value = "URL权限标识")
    private String urlPerm;

    /**
     * 按钮权限标识
     */
    @TableField(value = "btn_perm")
    @ApiModelProperty(value = "按钮权限标识")
    private String btnPerm;

    /**
     * 删除标识（0未删除 1已删除）
     */
    @TableLogic
    @TableField(value = "deleted")
    @ApiModelProperty(value = "删除标识（0未删除 1已删除）")
    private Boolean deleted;
}