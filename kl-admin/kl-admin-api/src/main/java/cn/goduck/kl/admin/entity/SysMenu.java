package cn.goduck.kl.admin.entity;

import cn.goduck.kl.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Desc: 菜单管理
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@ApiModel(value = "cn-goduck-kl-admin-entity-SysMenu")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_menu")
public class SysMenu extends BaseEntity {

    /**
     * 菜单名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 父菜单id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父菜单id")
    private Long parentId;

    /**
     * 路由名称
     */
    @TableField(value = "route_name")
    @ApiModelProperty(value = "路由名称")
    private String routeName;

    /**
     * 路由路径
     */
    @TableField(value = "route_path")
    @ApiModelProperty(value = "路由路径")
    private String routePath;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 跳转路径
     */
    @TableField(value = "redirect")
    @ApiModelProperty(value = "跳转路径")
    private String redirect;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 状态（0-开启 1-禁用)
     */
    @TableField(value = "visible")
    @ApiModelProperty(value = "状态（0-开启 1-禁用）")
    private Boolean visible;

    /**
     * 删除标识（0未删除 1已删除）
     */
    @TableLogic
    @TableField(value = "deleted")
    @ApiModelProperty(value = "删除标识（0未删除 1已删除）")
    private Boolean deleted;

    // ============================================附加字段============================================

    @TableField(exist = false)
    @ApiModelProperty(value = "角色集合")
    private List<String> roles;

}