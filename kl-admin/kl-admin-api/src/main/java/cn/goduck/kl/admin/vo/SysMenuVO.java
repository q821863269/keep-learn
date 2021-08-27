package cn.goduck.kl.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/27 15:29
 */
@Data
public class SysMenuVO {

    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父菜单id")
    private Long parentId;

    @ApiModelProperty(value = "路由名称")
    private String routeName;

    @ApiModelProperty(value = "路由路径")
    private String routePath;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "跳转路径")
    private String redirect;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态（0-开启 1-禁用）")
    private Boolean visible;

    @ApiModelProperty(value = "角色集合")
    private List<String> roles;

}
