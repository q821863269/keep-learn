package cn.goduck.kl.design.pattern.structure.composite.shape;

import java.awt.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 15:13
 */
public class Dot extends BaseShape {

    private final int DOT_SIZE = 3;

    public Dot(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public int getWidth() {
        return DOT_SIZE;
    }

    @Override
    public int getHeight() {
        return DOT_SIZE;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.fillRect(x - 1, y - 1, getWidth(), getHeight());
    }

}
