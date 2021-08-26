package cn.goduck.kl.admin.api;

import cn.goduck.kl.admin.dto.SysUserDTO;
import cn.goduck.kl.common.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-03 15:21
 */
@FeignClient(value = "kl-admin", contextId = "userFeignClient")
public interface UserFeignClient {

    @GetMapping("/users/username/{username}")
    R<SysUserDTO> getUserByUsername(@PathVariable("username") String username);

}