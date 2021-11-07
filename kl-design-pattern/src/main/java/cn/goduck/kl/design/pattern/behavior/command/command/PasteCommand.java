package cn.goduck.kl.design.pattern.behavior.command.command;

import cn.goduck.kl.design.pattern.behavior.command.editor.Editor;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 15:07
 */
public class PasteCommand extends Command {

    public PasteCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        if (editor.clipboard == null || editor.clipboard.isEmpty()) {
            return false;
        }

        backup();
        editor.textField.insert(editor.clipboard, editor.textField.getCaretPosition());
        return true;
    }

}
