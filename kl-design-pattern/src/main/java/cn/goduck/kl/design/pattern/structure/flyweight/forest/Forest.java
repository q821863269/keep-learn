package cn.goduck.kl.design.pattern.structure.flyweight.forest;

import cn.goduck.kl.design.pattern.structure.flyweight.tree.Tree;
import cn.goduck.kl.design.pattern.structure.flyweight.tree.TreeFactory;
import cn.goduck.kl.design.pattern.structure.flyweight.tree.TreeType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 9:31
 */
public class Forest extends JFrame {

    private List<Tree> treeList = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color, String otherTreeData) {
        TreeType type = TreeFactory.getTreeType(name, color, otherTreeData);
        Tree tree = new Tree(x, y, type);
        treeList.add(tree);
    }

    @Override
    public void paint(Graphics g) {
        for (Tree tree : treeList) {
            tree.draw(g);
        }
    }

}
