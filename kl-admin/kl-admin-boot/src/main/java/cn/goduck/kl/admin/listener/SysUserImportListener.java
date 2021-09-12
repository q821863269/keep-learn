package cn.goduck.kl.admin.listener;

import cn.goduck.kl.admin.entity.SysDept;
import cn.goduck.kl.admin.entity.SysRole;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.excel.SysUserExcel;
import cn.goduck.kl.admin.service.SysDeptService;
import cn.goduck.kl.admin.service.SysRoleService;
import cn.goduck.kl.admin.service.SysUserService;
import cn.goduck.kl.common.core.base.BaseEntity;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.common.web.util.SpringContextUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/11 13:36
 */
@Slf4j
public class SysUserImportListener extends AnalysisEventListener<SysUserExcel> {

    @Getter
    private final List<SysUser> dataList;

    @Getter
    private final List<String> errorList;

    private final Map<String, Long> deptMap;

    private final Map<String, Long> roleMap;

    private final SysUserService sysUserService;

    public SysUserImportListener() {
        dataList = new ArrayList<>();
        errorList = new ArrayList<>();
        sysUserService = SpringContextUtil.getBean(SysUserService.class);
        SysDeptService sysDeptService = SpringContextUtil.getBean(SysDeptService.class);
        SysRoleService sysRoleService = SpringContextUtil.getBean(SysRoleService.class);
        deptMap = sysDeptService.deptList().stream().collect(Collectors.toMap(SysDept::getName, BaseEntity::getId));
        roleMap = sysRoleService.roleList().stream().collect(Collectors.toMap(SysRole::getName, BaseEntity::getId));
    }

    @Override
    public void invoke(SysUserExcel data, AnalysisContext context) {
        String username = data.getUsername();
        // 校验用户名是否存在
        boolean exist = sysUserService.checkUsername(username);
        if (exist) {
            // rowIndex从0开始的
            int rowIndex = context.readRowHolder().getRowIndex() + 1;
            errorList.add("第 " + rowIndex + " 行，用户名【" + username + "】已存在");
            return;
        }

        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(data, sysUser);
        // 转换部门
        sysUser.setDeptId(deptMap.get(data.getDeptName()));
        // 转换角色
        List<Long> roleIds = new ArrayList<>();
        String roleNames = data.getRoleNames();
        if (StrUtil.isNotBlank(roleNames)) {
            List<String> roleNameList = CollectionUtil.toList(roleNames.split(StrConstant.COMMA));
            for (String roleName : roleNameList) {
                Long roleId = roleMap.get(roleName);
                if (ObjectUtil.isNotNull(roleId)) roleIds.add(roleId);
            }
        }
        sysUser.setRoleIds(roleIds);
        dataList.add(sysUser);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("SysUserImportListener 读取完毕");
    }

}