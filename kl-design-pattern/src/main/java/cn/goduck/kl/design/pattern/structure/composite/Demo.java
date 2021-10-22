package cn.goduck.kl.design.pattern.structure.composite;

import cn.goduck.kl.design.pattern.structure.composite.editor.ImageEditor;
import cn.goduck.kl.design.pattern.structure.composite.shape.Circle;
import cn.goduck.kl.design.pattern.structure.composite.shape.CompoundShape;
import cn.goduck.kl.design.pattern.structure.composite.shape.Dot;
import cn.goduck.kl.design.pattern.structure.composite.shape.Rectangle;

import java.awt.*;

/**
 * Desc: 组合模式
 *       组合模式是一种结构型设计模式， 你可以使用它将对象组合成树状结构， 并且能像使用独立对象一样使用它们。
 * Author: Kon
 * Date: 2021/10/22 15:22
 */
public class Demo {

    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();

        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),

                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Dot(160, 160, Color.RED)
                ),

                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )
        );
    }

}
