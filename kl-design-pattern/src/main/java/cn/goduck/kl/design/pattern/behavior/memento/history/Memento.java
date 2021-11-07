package cn.goduck.kl.design.pattern.behavior.memento.history;

import cn.goduck.kl.design.pattern.behavior.memento.editor.Editor;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 19:18
 */
public class Memento {

    private String backup;
    private Editor editor;

    public Memento(Editor editor) {
        this.editor = editor;
        this.backup = editor.backup();
    }

    public void restore() {
        editor.restore(backup);
    }

}
