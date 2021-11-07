package cn.goduck.kl.design.pattern.behavior.memento.shape;

import java.awt.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 19:05
 */
public class Rectangle extends BaseShape {

    private int width;
    private int height;

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
