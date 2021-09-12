package cn.goduck.kl.admin.api;

import cn.goduck.kl.admin.dto.UserDTO;
import cn.goduck.kl.common.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-03 15:21
 */
@FeignClient(value = "kl-admin", contextId = "userFeignClient")
@RequestMapping("/users")
public interface UserFeignClient {

    @GetMapping("/username/{username}")
    R<UserDTO> getUserByUsername(@PathVariable("username") String username);

}