package cn.goduck.kl.design.pattern.behavior.mediator.component;

import cn.goduck.kl.design.pattern.behavior.mediator.Mediator;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 18:11
 */
public class SaveButton extends JButton implements Component {

    private Mediator mediator;

    public SaveButton() {
        super("Save");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.saveChanges();
    }

    @Override
    public String getName() {
        return "SaveButton";
    }

}
