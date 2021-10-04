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
 * Desc: 角色表
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@ApiModel(value = "cn-goduck-kl-admin-entity-SysRole")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {
    /**
     * 角色名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 角色编码
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "角色编码")
    private String code;

    /**
     * 显示顺序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    /**
     * 数据权限
     */
    @TableField(value = "data_scope")
    @ApiModelProperty(value = "数据权限")
    private Integer dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "角色状态（0正常 1停用）")
    private Boolean status;

    /**
     * 删除标识（0未删除 1已删除）
     */
    @TableLogic
    @TableField(value = "deleted")
    @ApiModelProperty(value = "删除标识（0未删除 1已删除）")
    private Boolean deleted;

    // ============================================附加字段============================================

    @TableField(exist = false)
    private List<Long> menuIds;

    @TableField(exist = false)
    private List<Long> permissionIds;

}