package cn.goduck.kl.design.pattern.structure.composite.shape;

import java.awt.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 15:14
 */
public class Rectangle extends BaseShape {

    public int width;
    public int height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawRect(x, y, getWidth() - 1, getHeight() - 1);
    }

}
