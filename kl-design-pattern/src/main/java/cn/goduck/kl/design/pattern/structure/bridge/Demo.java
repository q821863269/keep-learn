package cn.goduck.kl.design.pattern.structure.bridge;

import cn.goduck.kl.design.pattern.structure.bridge.device.Device;
import cn.goduck.kl.design.pattern.structure.bridge.device.Radio;
import cn.goduck.kl.design.pattern.structure.bridge.device.Tv;
import cn.goduck.kl.design.pattern.structure.bridge.remote.AdvancedRemote;
import cn.goduck.kl.design.pattern.structure.bridge.remote.BasicRemote;

/**
 * Desc: 桥接模式
 *       桥接模式是一种结构型设计模式， 可将一个大类或一系列紧密相关的类拆分为抽象和实现两个独立的层次结构， 从而能在开发时分别使用。
 * Author: Kon
 * Date: 2021/10/22 14:55
 */
public class Demo {

    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }

}
