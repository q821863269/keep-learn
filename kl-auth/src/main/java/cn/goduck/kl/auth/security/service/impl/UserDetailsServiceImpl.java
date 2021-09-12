package cn.goduck.kl.auth.security.service.impl;

import cn.goduck.kl.admin.api.UserFeignClient;
import cn.goduck.kl.admin.dto.UserDTO;
import cn.goduck.kl.auth.domain.OAuthUserDetails;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.goduck.kl.common.web.exception.BizException;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-03 15:00
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO：这里要区分一下是管理员还是会员，目前只做管理员部分
        OAuthUserDetails oauthUserDetails;
        R<UserDTO> result = userFeignClient.getUserByUsername(username);
        log.info("远程调用：userFeignClient.getUserByUsername");
        // 判断远程调用查询是否成功
        if (R.isOk(result) && ObjectUtil.isNotNull(result.getData())) {
            UserDTO userDTO = result.getData();
            oauthUserDetails = new OAuthUserDetails(userDTO);
        } else{
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        }
        if (!oauthUserDetails.isEnabled()) {
            throw new BizException(ResultCode.USER_ACCOUNT_DISABLED);
        } else if (!oauthUserDetails.isAccountNonLocked()) {
            throw new BizException(ResultCode.USER_ACCOUNT_LOCKED);
        } else if (!oauthUserDetails.isAccountNonExpired()) {
            throw new BizException(ResultCode.USER_ACCOUNT_EXPIRED);
        }
        return oauthUserDetails;
    }

}