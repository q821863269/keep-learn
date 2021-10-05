package cn.goduck.kl.admin.entity;

import cn.goduck.kl.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc: 客户端信息表
 * Author: Kon
 * Date: 2021/6/24 15:30
 */
@ApiModel(value = "cn-goduck-kl-admin-entity-SysOauthClient")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_oauth_client")
public class SysOauthClient extends BaseEntity {

    /**
     * 客户端id
     */
    @TableField(value = "client_id")
    @ApiModelProperty(value = "客户端id")
    private String clientId;

    /**
     * 资源id列表
     */
    @TableField(value = "resource_ids")
    @ApiModelProperty(value = "资源id列表")
    private String resourceIds;

    /**
     * 客户端密钥
     */
    @TableField(value = "client_secret")
    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    /**
     * 域
     */
    @TableField(value = "`scope`")
    @ApiModelProperty(value = "域")
    private String scope;

    /**
     * 授权方式
     */
    @TableField(value = "authorized_grant_types")
    @ApiModelProperty(value = "授权方式")
    private String authorizedGrantTypes;

    /**
     * 回调地址
     */
    @TableField(value = "web_server_redirect_uri")
    @ApiModelProperty(value = "回调地址")
    private String webServerRedirectUri;

    /**
     * 权限列表
     */
    @TableField(value = "authorities")
    @ApiModelProperty(value = "权限列表")
    private String authorities;

    /**
     * 认证令牌时效
     */
    @TableField(value = "access_token_validity")
    @ApiModelProperty(value = "认证令牌时效")
    private Integer accessTokenValidity;

    /**
     * 刷新令牌时效
     */
    @TableField(value = "refresh_token_validity")
    @ApiModelProperty(value = "刷新令牌时效")
    private Integer refreshTokenValidity;

    /**
     * 扩展信息
     */
    @TableField(value = "additional_information")
    @ApiModelProperty(value = "扩展信息")
    private String additionalInformation;

    /**
     * 是否自动放行
     */
    @TableField(value = "auto_approve")
    @ApiModelProperty(value = "是否自动放行")
    private Boolean autoApprove;
}