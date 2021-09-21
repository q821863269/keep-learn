package cn.goduck.kl.admin.query;

import cn.goduck.kl.common.core.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/21 22:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典列表查询")
public class SysDictQuery extends BasePageQuery<Object> {

    @ApiModelProperty(value = "字典名称")
    private String name;

}
