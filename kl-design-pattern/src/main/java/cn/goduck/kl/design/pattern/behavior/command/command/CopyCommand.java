package cn.goduck.kl.design.pattern.behavior.command.command;

import cn.goduck.kl.design.pattern.behavior.command.editor.Editor;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 15:04
 */
public class CopyCommand extends Command {

    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.clipboard = editor.textField.getSelectedText();
        return false;
    }

}
