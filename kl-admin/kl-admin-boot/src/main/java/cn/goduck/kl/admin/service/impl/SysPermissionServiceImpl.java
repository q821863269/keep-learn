package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysPermission;
import cn.goduck.kl.admin.mapper.SysPermissionMapper;
import cn.goduck.kl.admin.query.SysPermissionQuery;
import cn.goduck.kl.admin.service.SysPermissionService;
import cn.goduck.kl.common.core.constant.RedisConstant;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
@AllArgsConstructor
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<String> listBtnPermByRoles(List<String> roles) {
        return this.baseMapper.listBtnPermByRoles(roles);
    }

    @Override
    public IPage<SysPermission> page(Page<SysPermission> page, SysPermissionQuery sysPermissionQuery) {
        return this.baseMapper.page(page, sysPermissionQuery);
    }

    @Override
    public boolean refreshPermRolesRules() {
        // 删除redis中url权限和btn权限缓存
        redisTemplate.delete(Arrays.asList(RedisConstant.URL_PERM_ROLES_KEY, RedisConstant.BTN_PERM_ROLES_KEY));
        List<SysPermission> sysPermissionList = this.baseMapper.permRolesList();
        if (CollectionUtil.isNotEmpty(sysPermissionList)) {
            // 初始化URL【权限->角色(集合)】规则
            List<SysPermission> urlPermList = sysPermissionList.stream()
                    .filter(item -> StrUtil.isNotBlank(item.getUrlPerm()))
                    .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(urlPermList)) {
                Map<String, List<String>> urlPermRolesMap = new HashMap<>();
                urlPermList.forEach(item -> urlPermRolesMap.put(item.getUrlPerm(), item.getRoles()));
                // 设置url权限对应的角色到缓存
                redisTemplate.opsForHash().putAll(RedisConstant.URL_PERM_ROLES_KEY, urlPermRolesMap);
                // 发送消息通知网关，清除本地缓存
                redisTemplate.convertAndSend(RedisConstant.CLEAN_ROLE_LOCAL_CACHE, StrConstant.EMPTY);
            }
            // 初始化URL【按钮->角色(集合)】规则
            List<SysPermission> btnPermList = sysPermissionList.stream()
                    .filter(item -> StrUtil.isNotBlank(item.getBtnPerm()))
                    .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(btnPermList)) {
                Map<String, List<String>> btnPermRolesMap = new HashMap<>();
                btnPermList.forEach(item -> btnPermRolesMap.put(item.getBtnPerm(), item.getRoles()));
                // 设置btn权限对应的角色到缓存
                redisTemplate.opsForHash().putAll(RedisConstant.BTN_PERM_ROLES_KEY, btnPermRolesMap);
            }
        }
        return true;
    }

}