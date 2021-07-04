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
 * Desc: 部门表
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@ApiModel(value = "cn-goduck-kl-admin-entity-SysDept")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_dept")
public class SysDept extends BaseEntity {

    /**
     * 部门名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "部门名称")
    private String name;

    /**
     * 父节点id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父节点id")
    private Integer parentId;

    /**
     * 父节点id路径
     */
    @TableField(value = "tree_path")
    @ApiModelProperty(value = "父节点id路径")
    private String treePath;

    /**
     * 显示顺序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    /**
     * 状态（0-正常 1-禁用）
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态（0-正常 1-禁用）")
    private Boolean status;

    /**
     * 删除标识（0未删除 1已删除）
     */
    @TableLogic
    @TableField(value = "deleted")
    @ApiModelProperty(value = "删除标识（0未删除 1已删除）")
    private Boolean deleted;
}