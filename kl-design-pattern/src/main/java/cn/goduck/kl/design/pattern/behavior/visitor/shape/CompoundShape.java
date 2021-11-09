package cn.goduck.kl.design.pattern.behavior.visitor.shape;

import cn.goduck.kl.design.pattern.behavior.visitor.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 17:49
 */
public class CompoundShape implements Shape {

    public int id;
    public List<Shape> children = new ArrayList<>();

    public CompoundShape(int id) {
        this.id = id;
    }

    @Override
    public void move(int x, int y) {
        // move shape
    }

    @Override
    public void draw() {
        // draw shape
    }

    public int getId() {
        return id;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCompoundGraphic(this);
    }

    public void add(Shape shape) {
        children.add(shape);
    }

}
