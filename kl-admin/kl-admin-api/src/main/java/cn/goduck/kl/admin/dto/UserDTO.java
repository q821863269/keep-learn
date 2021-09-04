package cn.goduck.kl.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-03 15:44
 * @see cn.goduck.kl.admin.entity.SysUser
 */
@Data
public class UserDTO {

    private Long id;

    private String username;

    private String nickname;

    private String password;

    private Boolean status;

    private List<String> roles;

}