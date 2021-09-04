package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysDept;
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

    List<DeptVO> listTable(Integer status, String name);

    List<TreeVO> listSelect();

}