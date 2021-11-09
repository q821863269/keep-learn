package cn.goduck.kl.design.pattern.behavior.observer.editor;

import cn.goduck.kl.design.pattern.behavior.observer.publisher.EventManager;

import java.io.File;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 14:29
 */
public class Editor {

    public EventManager events;
    private File file;

    public Editor() {
        this.events = new EventManager("open", "save");
    }

    public void openFile(String filePath) {
        this.file = new File(filePath);
        events.notify("open", file);
    }

    public void saveFile() throws Exception {
        if (this.file != null) {
            events.notify("save", file);
        } else {
            throw new Exception("Please open a file first.");
        }
    }

}
