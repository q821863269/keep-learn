package cn.goduck.kl.design.pattern.behavior.visitor.shape;

import cn.goduck.kl.design.pattern.behavior.visitor.visitor.Visitor;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 17:47
 */
public class Circle extends Dot {

    private int radius;

    public Circle(int id, int x, int y, int radius) {
        super(id, x, y);
        this.radius = radius;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCircle(this);
    }

    public int getRadius() {
        return radius;
    }

}