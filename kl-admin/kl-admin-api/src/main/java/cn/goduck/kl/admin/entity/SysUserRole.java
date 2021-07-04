package cn.goduck.kl.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Desc: 用户和角色关联表
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@ApiModel(value = "cn-goduck-kl-admin-entity-SysUserRole")
@Data
@TableName(value = "sys_user_role")
public class SysUserRole {
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色id")
    private Long roleId;
}