package cn.goduck.kl.design.pattern.behavior.mediator.component;

import cn.goduck.kl.design.pattern.behavior.mediator.Mediator;
import cn.goduck.kl.design.pattern.behavior.mediator.Note;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 18:07
 */
public class AddButton extends JButton implements Component {

    private Mediator mediator;

    public AddButton() {
        super("Add");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.addNewNote(new Note());
    }

    @Override
    public String getName() {
        return "AddButton";
    }

}
