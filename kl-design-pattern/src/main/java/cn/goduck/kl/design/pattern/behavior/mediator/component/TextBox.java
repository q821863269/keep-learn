package cn.goduck.kl.design.pattern.behavior.mediator.component;

import cn.goduck.kl.design.pattern.behavior.mediator.Mediator;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 18:11
 */
public class TextBox extends JTextArea implements Component {

    private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void processComponentKeyEvent(KeyEvent keyEvent) {
        mediator.markNote();
    }

    @Override
    public String getName() {
        return "TextBox";
    }

}