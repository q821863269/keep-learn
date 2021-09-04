package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysDept;
import cn.goduck.kl.admin.mapper.SysDeptMapper;
import cn.goduck.kl.admin.service.SysDeptService;
import cn.goduck.kl.admin.vo.DeptVO;
import cn.goduck.kl.admin.vo.TreeVO;
import cn.goduck.kl.common.core.base.BaseEntity;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<DeptVO> listTable(Integer status, String name) {
        LambdaQueryWrapper<SysDept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ObjectUtil.isNotNull(status), SysDept::getStatus, status);
        lambdaQueryWrapper.eq(StrUtil.isNotBlank(name), SysDept::getName, name);
        lambdaQueryWrapper.orderByAsc(SysDept::getSort);
        List<SysDept> deptList = this.list(lambdaQueryWrapper);
        return recursionTableList(GlobalConstant.DEPT_ROOT_ID, deptList);
    }

    /**
     * 递归生成部门表格层级列表
     *
     * @param parentId 父级Id
     * @param deptList 部门集合
     * @return 部门表格层级列表
     */
    private static List<DeptVO> recursionTableList(Long parentId, List<SysDept> deptList) {
        List<DeptVO> deptTableList = new ArrayList<>();
        Optional.ofNullable(deptList).orElse(new ArrayList<>())
                .stream()
                .filter(dept -> parentId.equals(dept.getParentId()))
                .forEach(dept -> {
                    DeptVO deptVO = new DeptVO();
                    BeanUtil.copyProperties(dept, deptVO);
                    List<DeptVO> children = recursionTableList(dept.getId(), deptList);
                    deptVO.setChildren(children);
                    deptTableList.add(deptVO);
                });
        return deptTableList;
    }

    @Override
    public List<TreeVO> listSelect() {
        LambdaQueryWrapper<SysDept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysDept::getStatus, GlobalConstant.VALID);
        lambdaQueryWrapper.orderByAsc(SysDept::getSort);
        lambdaQueryWrapper.select(BaseEntity::getId, SysDept::getName, SysDept::getParentId);
        List<SysDept> deptList = this.list(lambdaQueryWrapper);
        return recursionSelectList(GlobalConstant.DEPT_ROOT_ID, deptList);
    }

    /**
     * 递归生成部门树
     *
     * @param parentId 父级Id
     * @param deptList 部门集合
     * @return 部门树
     */
    private List<TreeVO> recursionSelectList(Long parentId, List<SysDept> deptList) {
        List<TreeVO> deptSelectList = new ArrayList<>();
        Optional.ofNullable(deptList).orElse(new ArrayList<>())
                .stream()
                .filter(dept -> parentId.equals(dept.getParentId()))
                .forEach(dept -> {
                    TreeVO treeVO = new TreeVO();
                    treeVO.setId(dept.getId());
                    treeVO.setLabel(dept.getName());
                    List<TreeVO> children = recursionSelectList(dept.getId(), deptList);
                    treeVO.setChildren(children);
                    deptSelectList.add(treeVO);
                });
        return deptSelectList;
    }

}