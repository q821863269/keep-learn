package cn.goduck.kl.design.pattern.creation.abstractfactory;

import cn.goduck.kl.design.pattern.creation.abstractfactory.app.Application;
import cn.goduck.kl.design.pattern.creation.abstractfactory.factory.GUIFactory;
import cn.goduck.kl.design.pattern.creation.abstractfactory.factory.MacOSFactory;
import cn.goduck.kl.design.pattern.creation.abstractfactory.factory.WindowsFactory;

/**
 * Desc: 抽象工厂模式
 *       抽象工厂模式是一种创建型设计模式， 它能创建一系列相关的对象， 而无需指定其具体类。
 * Author: Kon
 * Date: 2021/10/21 17:01
 */
public class Demo {

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }

    private static Application configureApplication() {
        String osName = System.getProperty("os.name").toLowerCase();
        GUIFactory factory;
        if (osName.contains("windows 10")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacOSFactory();
        }
        return new Application(factory);
    }

}
