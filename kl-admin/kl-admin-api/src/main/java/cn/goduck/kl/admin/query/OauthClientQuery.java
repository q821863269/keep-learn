package cn.goduck.kl.admin.query;

import cn.goduck.kl.admin.entity.SysOauthClient;
import cn.goduck.kl.common.core.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/30 17:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "客户端列表查询")
public class OauthClientQuery extends BasePageQuery<SysOauthClient> {

    @ApiModelProperty(value = "昵称")
    private String clientId;

}
