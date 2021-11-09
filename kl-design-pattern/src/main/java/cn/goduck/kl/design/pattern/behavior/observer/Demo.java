package cn.goduck.kl.design.pattern.behavior.observer;

import cn.goduck.kl.design.pattern.behavior.observer.editor.Editor;
import cn.goduck.kl.design.pattern.behavior.observer.listeners.EmailNotificationListener;
import cn.goduck.kl.design.pattern.behavior.observer.listeners.LogOpenListener;

/**
 * Desc: 观察者模式
 * Author: Kon
 * Date: 2021/11/7 11:27
 */
public class Demo {

    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener("/path/to/log/file.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
