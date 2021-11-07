package cn.goduck.kl.design.pattern.behavior.mediator;

import cn.goduck.kl.design.pattern.behavior.mediator.component.*;

import javax.swing.*;

/**
 * Desc: 中介者模式
 * Author: Kon
 * Date: 2021/11/7 11:27
 */
public class Demo {

    public static void main(String[] args) {
        Mediator mediator = new Editor();

        mediator.registerComponent(new Title());
        mediator.registerComponent(new TextBox());
        mediator.registerComponent(new AddButton());
        mediator.registerComponent(new DeleteButton());
        mediator.registerComponent(new SaveButton());
        mediator.registerComponent(new List(new DefaultListModel()));
        mediator.registerComponent(new Filter());

        mediator.createGUI();
    }

}
