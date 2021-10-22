package cn.goduck.kl.design.pattern.structure.composite.shape;

import java.awt.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 15:06
 */
public interface Shape {

    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void move(int x, int y);
    boolean isInsideBounds(int x, int y);
    void select();
    void unSelect();
    boolean isSelected();
    void paint(Graphics graphics);

}
