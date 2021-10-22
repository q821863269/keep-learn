package cn.goduck.kl.design.pattern.structure.composite.shape;

import java.awt.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 15:14
 */
public class Circle extends BaseShape {

    public int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public int getWidth() {
        return radius * 2;
    }

    @Override
    public int getHeight() {
        return radius * 2;
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawOval(x, y, getWidth() - 1, getHeight() - 1);
    }

}
