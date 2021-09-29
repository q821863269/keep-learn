package cn.goduck.kl.admin.entity;

import cn.goduck.kl.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc: 字典数据表
 * Author: Kon
 * Date: 2021/9/29 10:28
 */
@ApiModel(value="cn-goduck-kl-admin-entity-SysDictItem")
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "sys_dict_item")
public class SysDictItem extends BaseEntity {

    /**
     * 字典项名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="字典项名称")
    private String name;

    /**
     * 字典项值
     */
    @TableField(value = "value")
    @ApiModelProperty(value="字典项值")
    private String value;

    /**
     * 字典编码
     */
    @TableField(value = "dict_code")
    @ApiModelProperty(value="字典编码")
    private String dictCode;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="排序")
    private Integer sort;

    /**
     * 状态（0 停用 1正常）
     */
    @TableField(value = "status")
    @ApiModelProperty(value="状态（0 停用 1正常）")
    private Boolean status;

    /**
     * 是否默认（0否 1是）
     */
    @TableField(value = "defaulted")
    @ApiModelProperty(value="是否默认（0否 1是）")
    private Boolean defaulted;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;
}