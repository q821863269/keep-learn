package cn.goduck.kl.design.pattern.behavior.visitor;

import cn.goduck.kl.design.pattern.behavior.visitor.shape.*;
import cn.goduck.kl.design.pattern.behavior.visitor.visitor.XMLExportVisitor;

/**
 * Desc: 访问者模式
 * Author: Kon
 * Date: 2021/11/7 11:27
 */
public class Demo {

    public static void main(String[] args) {
        Dot dot = new Dot(1, 10, 55);
        Circle circle = new Circle(2, 23, 15, 10);
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

        CompoundShape compoundShape = new CompoundShape(4);
        compoundShape.add(dot);
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        CompoundShape c = new CompoundShape(5);
        c.add(dot);
        compoundShape.add(c);

        export(circle, compoundShape);
    }

    private static void export(Shape... shapes) {
        XMLExportVisitor exportVisitor = new XMLExportVisitor();
        System.out.println(exportVisitor.export(shapes));
    }

}
