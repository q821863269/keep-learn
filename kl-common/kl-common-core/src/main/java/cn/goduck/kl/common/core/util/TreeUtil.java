package cn.goduck.kl.common.core.util;

import cn.goduck.kl.common.core.vo.TreeVO;
import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/19 15:50
 */
public class TreeUtil {

    public static List<TreeVO> addTop(List<TreeVO> treeVOList) {
        if (CollectionUtil.isNotEmpty(treeVOList)) {
            TreeVO treeVO = new TreeVO(0L, "无", treeVOList);
            treeVOList = new ArrayList<>();
            treeVOList.add(treeVO);
        }
        return treeVOList;
    }

}
