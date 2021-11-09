package cn.goduck.kl.design.pattern.behavior.visitor.shape;

import cn.goduck.kl.design.pattern.behavior.visitor.visitor.Visitor;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 17:47
 */
public interface Shape {

    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);

}
