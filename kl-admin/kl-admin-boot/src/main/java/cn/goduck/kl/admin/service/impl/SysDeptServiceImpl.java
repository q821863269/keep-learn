package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysDept;
import cn.goduck.kl.admin.mapper.SysDeptMapper;
import cn.goduck.kl.admin.query.SysDeptQuery;
import cn.goduck.kl.admin.service.SysDeptService;
import cn.goduck.kl.admin.vo.DeptVO;
import cn.goduck.kl.admin.vo.TreeVO;
import cn.goduck.kl.common.core.base.BaseEntity;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
@SuppressWarnings("unchecked")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<DeptVO> tableList(SysDeptQuery sysDeptQuery) {
        LambdaQueryWrapper<SysDept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StrUtil.isNotBlank(sysDeptQuery.getName()), SysDept::getName, sysDeptQuery.getName());
        lambdaQueryWrapper.eq(ObjectUtil.isNotNull(sysDeptQuery.getStatus()), SysDept::getStatus, sysDeptQuery.getStatus());
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
    public List<TreeVO> selectList() {
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

    @Override
    public List<SysDept> deptList() {
        LambdaQueryWrapper<SysDept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysDept::getStatus, GlobalConstant.VALID);
        lambdaQueryWrapper.orderByAsc(SysDept::getSort);
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public boolean saveDept(SysDept sysDept) {
        String deptTreePath = getDeptTreePath(sysDept);
        sysDept.setTreePath(deptTreePath);
        sysDept.setDefaultFieldValue();
        return this.saveOrUpdate(sysDept);
    }

    /**
     * 获取部门级联路径
     *
     * @param dept 部门实体
     * @return 级联路径
     */
    private String getDeptTreePath(SysDept dept) {
        Long parentId = dept.getParentId();
        String treePath;
        if (GlobalConstant.DEPT_ROOT_ID.equals(parentId)) {
            treePath = String.valueOf(GlobalConstant.DEPT_ROOT_ID);
        } else {
            SysDept parentDept = this.getById(parentId);
            treePath = Optional.ofNullable(parentDept)
                    .map(item -> item.getTreePath() + StrConstant.COMMA + item.getId())
                    .orElse(Strings.EMPTY);
        }
        return treePath;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(String ids) {
        AtomicBoolean result = new AtomicBoolean(true);
        List<String> idList = Arrays.asList(ids.split(StrConstant.COMMA));
        // 删除部门及子部门
        idList.forEach(id -> {
                    boolean removeResult = this.remove(
                            new LambdaQueryWrapper<SysDept>()
                                    .eq(BaseEntity::getId, id)
                                    .or()
                                    .apply("concat (',',tree_path,',') like concat('%,',{0},',%')", id));
                    if (!removeResult) result.set(Boolean.FALSE);
                });
        return result.get();
    }

}