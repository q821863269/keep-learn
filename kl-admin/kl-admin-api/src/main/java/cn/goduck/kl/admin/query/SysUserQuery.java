package cn.goduck.kl.admin.query;

import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.common.core.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/30 15:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户列表查询")
public class SysUserQuery extends BasePageQuery<SysUser> {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "状态（0-正常 1-禁用）")
    private Integer status;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

}
