package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.dto.UserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.entity.SysUserRole;
import cn.goduck.kl.admin.excel.SysUserExcel;
import cn.goduck.kl.admin.listener.SysUserImportListener;
import cn.goduck.kl.admin.mapper.SysUserMapper;
import cn.goduck.kl.admin.query.SysUserQuery;
import cn.goduck.kl.admin.service.SysUserRoleService;
import cn.goduck.kl.admin.service.SysUserService;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.goduck.kl.common.core.util.EasyExcelUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/24 15:39
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final PasswordEncoder passwordEncoder;

    private final SysUserRoleService sysUserRoleService;

    @Override
    public UserDTO getByUsername(String username) {
        return this.baseMapper.getByUsername(username);
    }

    @Override
    public boolean checkUsername(String username) {
        int count = this.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        return count > 0;
    }

    @Override
    public IPage<SysUser> page(SysUserQuery sysUserQuery) {
        return this.baseMapper.page(sysUserQuery.page(), sysUserQuery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUser(SysUser sysUser) {
        // 设置默认密码
        sysUser.setPassword(passwordEncoder.encode(GlobalConstant.DEFAULT_USER_PASSWORD));
        sysUser.setCreateFieldValue();
        // 保存用户
        boolean result = this.save(sysUser);
        if (result) {
            List<Long> roleIds = sysUser.getRoleIds();
            if (CollectionUtil.isNotEmpty(roleIds)) {
                List<SysUserRole> userRoleList = new ArrayList<>();
                roleIds.forEach(roleId -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(sysUser.getId());
                    sysUserRole.setRoleId(roleId);
                    sysUserRole.setCreateFieldValue();
                    userRoleList.add(sysUserRole);
                });
                // 保存用户角色关系
                result = sysUserRoleService.saveBatch(userRoleList);
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(SysUser sysUser) {
        Long userId = sysUser.getId();
        List<Long> dbRoleIdList = sysUserRoleService.roleIdList(userId);
        List<Long> userRoleIdList = sysUser.getRoleIds();
        // 增加用户角色
        List<Long> addRoleIdList = userRoleIdList.stream().filter(roleId -> !dbRoleIdList.contains(roleId)).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(addRoleIdList)) {
            List<SysUserRole> addList = new ArrayList<>();
            addRoleIdList.forEach(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                sysUserRole.setCreateFieldValue();
                addList.add(sysUserRole);
            });
            sysUserRoleService.saveBatch(addList);
        }
        // 删除用户角色
        List<Long> removeRoleIdList = dbRoleIdList.stream().filter(roleId -> !userRoleIdList.contains(roleId)).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(removeRoleIdList)) {
            removeRoleIdList.forEach(roleId -> sysUserRoleService.remove(
                    new LambdaQueryWrapper<SysUserRole>()
                            .eq(SysUserRole::getUserId, userId)
                            .eq(SysUserRole::getRoleId, roleId)));
        }
        // 更新用户
        sysUser.setUpdateFieldValue();
        return this.updateById(sysUser);
    }

    @Override
    public boolean patchUser(SysUser sysUser) {
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<SysUser>().eq(SysUser::getId, sysUser.getId());
        updateWrapper.set(ObjectUtil.isNotNull(sysUser.getStatus()), SysUser::getStatus, sysUser.getStatus());
        if (ObjectUtil.isNotNull(sysUser.getPassword())) {
            updateWrapper.set(SysUser::getPassword, passwordEncoder.encode(sysUser.getPassword()));
        }
        sysUser.setUpdateFieldValue();
        return this.update(updateWrapper);
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String excelImport(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            SysUserImportListener sysUserImportListener = new SysUserImportListener();
            EasyExcel.read(is, SysUserExcel.class, sysUserImportListener).sheet().doRead();
            List<String> errorList = sysUserImportListener.getErrorList();
            if (CollectionUtil.isNotEmpty(errorList)) {
                StringBuilder sb = new StringBuilder();
                errorList.forEach(e -> sb.append(e).append("<br />"));
                String errorMsg = sb.toString();
                return errorMsg.substring(0, errorMsg.length() - 6);
            } else {
                // 入库
                List<SysUser> dataList = sysUserImportListener.getDataList();
                dataList.forEach(this::saveUser);
            }
        }
        return null;
    }

    @SneakyThrows
    @Override
    public void excelExport(HttpServletResponse response, SysUserQuery sysUserQuery) {
        List<SysUserExcel> sysUserExcelList;
        if (sysUserQuery.exportEmptyExcel()) {
            sysUserExcelList = new ArrayList<>();
        } else {
            IPage<SysUserExcel> sysUserExcelIPage = this.baseMapper.userList(sysUserQuery.exportPage(), sysUserQuery);
            sysUserExcelList = sysUserExcelIPage.getRecords();
        }
        // 设置response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(GlobalConstant.UTF8);
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode("用户信息" + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_FORMAT), GlobalConstant.UTF8);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ExcelTypeEnum.XLSX.getValue());
        EasyExcel.write(response.getOutputStream(), SysUserExcel.class)
                .registerWriteHandler(EasyExcelUtil.excelStyle())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("用户信息")
                .doWrite(sysUserExcelList);
    }

}