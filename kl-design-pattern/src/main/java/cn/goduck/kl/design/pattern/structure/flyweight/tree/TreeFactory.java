package cn.goduck.kl.design.pattern.structure.flyweight.tree;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 9:25
 */
public class TreeFactory {

    private static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String name, Color color, String otherTreeData) {
        TreeType result = treeTypeMap.get(name);
        if (result == null) {
            result = new TreeType(name, color, otherTreeData);
            treeTypeMap.put(name, result);
        }
        return result;
    }

}
