package cn.goduck.kl.admin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/21 17:09
 */
@Data
@Accessors(chain = true)
public class RolePermissionDTO {

    private Long roleId;

    private Long menuId;

    private List<Long> permissionIds;

}
