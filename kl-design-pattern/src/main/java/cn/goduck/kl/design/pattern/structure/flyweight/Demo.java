package cn.goduck.kl.design.pattern.structure.flyweight;

import cn.goduck.kl.design.pattern.structure.flyweight.forest.Forest;
import lombok.SneakyThrows;

import java.awt.*;

/**
 * Desc: 享元模式
 *       享元模式是一种结构型设计模式， 它摒弃了在每个对象中保存所有数据的方式， 通过共享多个对象所共有的相同状态， 让你能在有限的内存容量中载入更多对象。
 * Author: Kon
 * Date: 2021/11/7 9:04
 */
public class Demo {

    static int CANVAS_SIZE = 500;
    static int TREES_TO_DRAW = 50000;
    static int TREE_TYPES = 2;

    @SneakyThrows
    public static void main(String[] args) {
        Forest forest = new Forest();
        for (int i = 0; i < Math.floor((float) (TREES_TO_DRAW / TREE_TYPES)); i++) {
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE), "Summer Oak", Color.GREEN, "Oak texture stub");
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE), "Autumn Oak", Color.ORANGE, "Autumn Oak texture stub");
        }

        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setVisible(true);
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * (max - min) + 1);
    }

}
