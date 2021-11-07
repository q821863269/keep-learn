package cn.goduck.kl.design.pattern.structure.flyweight.tree;

import java.awt.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 9:22
 */
public class Tree {

    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }

}
