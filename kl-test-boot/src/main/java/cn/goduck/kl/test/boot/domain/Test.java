package cn.goduck.kl.test.boot.domain;

import cn.goduck.kl.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-06-20 15:09
 */
@ApiModel(value = "cn-goduck-kl-test-boot-domain-Test")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "test")
public class Test extends BaseEntity {

    @TableField(value = "`name`")
    @ApiModelProperty(value = "名称")
    private String name;

}