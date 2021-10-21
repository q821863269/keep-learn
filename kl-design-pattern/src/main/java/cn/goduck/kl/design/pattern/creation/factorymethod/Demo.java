package cn.goduck.kl.design.pattern.creation.factorymethod;

import cn.goduck.kl.design.pattern.creation.factorymethod.factory.Dialog;
import cn.goduck.kl.design.pattern.creation.factorymethod.factory.HtmlDialog;
import cn.goduck.kl.design.pattern.creation.factorymethod.factory.WindowsDialog;

/**
 * Desc: 工厂方法模式
 *       工厂方法模式是一种创建型设计模式， 其在父类中提供一个创建对象的方法， 允许子类决定实例化对象的类型。
 * Author: Kon
 * Date: 2021/10/21 16:17
 */
public class Demo {

    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    private static void configure() {
        System.out.println(System.getProperty("os.name"));
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    private static void runBusinessLogic() {
        dialog.renderWindow();
    }

}
