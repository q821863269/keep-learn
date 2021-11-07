package cn.goduck.kl.design.pattern.behavior.command.command;

import cn.goduck.kl.design.pattern.behavior.command.editor.Editor;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 14:52
 */
public abstract class Command {

    public Editor editor;
    private String backup;

    public Command(Editor editor) {
        this.editor = editor;
    }

    void backup() {
        backup = editor.textField.getText();
    }

    public void undo() {
        editor.textField.setText(backup);
    }

    public abstract boolean execute();

}
