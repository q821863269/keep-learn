package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysDept;
import cn.goduck.kl.admin.query.SysDeptQuery;
import cn.goduck.kl.admin.vo.DeptVO;
import cn.goduck.kl.admin.vo.TreeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysDeptService extends IService<SysDept> {

    List<DeptVO> listTable(SysDeptQuery sysDeptQuery);

    List<TreeVO> listSelect();

    List<SysDept> deptList();

    boolean saveDept(SysDept sysDept);

    boolean deleteByIds(String ids);

}