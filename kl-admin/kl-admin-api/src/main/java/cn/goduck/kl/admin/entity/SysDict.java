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
 * Desc: 字典类型表
 * Author: Kon
 * Date: 2021/9/29 10:27
 */
@ApiModel(value="cn-goduck-kl-admin-entity-SysDict")
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "sys_dict")
public class SysDict extends BaseEntity {

    /**
     * 类型名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="类型名称")
    private String name;

    /**
     * 类型编码
     */
    @TableField(value = "code")
    @ApiModelProperty(value="类型编码")
    private String code;

    /**
     * 状态（0-正常 ,1-停用）
     */
    @TableField(value = "status")
    @ApiModelProperty(value="状态（0-正常 ,1-停用）")
    private Boolean status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 删除标识（0未删除 1已删除）
     */
    @TableLogic
    @TableField(value = "deleted")
    @ApiModelProperty(value="删除标识（0未删除 1已删除）")
    private Boolean deleted;
}