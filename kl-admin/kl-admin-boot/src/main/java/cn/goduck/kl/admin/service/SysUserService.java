package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.dto.UserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.query.SysUserQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/24 15:39
 */
public interface SysUserService extends IService<SysUser> {

    UserDTO getByUsername(String username);

    boolean checkUsername(String username);

    IPage<SysUser> page(SysUserQuery sysUserQuery);

    boolean saveUser(SysUser sysUser);

    boolean updateUser(SysUser sysUser);

    boolean patchUser(SysUser sysUser);

    String excelImport(MultipartFile file);

    void excelExport(HttpServletResponse response, SysUserQuery sysUserQuery);

}