package cn.goduck.kl.admin.excel;

import cn.goduck.kl.common.core.converter.GenderConverter;
import cn.goduck.kl.common.core.converter.StatusConverter;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/11 11:57
 */
@Data
public class SysUserExcel {

    @ExcelProperty(value = "用户名")
    private String username;

    @ExcelProperty(value = "昵称")
    private String nickname;

    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    private Integer gender;

    @ExcelProperty(value = "部门名称")
    private String deptName;

    @ExcelProperty(value = "角色名称")
    private String roleNames;

    @ExcelProperty(value = "手机号码")
    private String mobile;

    @ExcelProperty(value = "用户邮箱")
    private String email;

    @ExcelProperty(value = "用户状态", converter = StatusConverter.class)
    private Boolean status;

}
