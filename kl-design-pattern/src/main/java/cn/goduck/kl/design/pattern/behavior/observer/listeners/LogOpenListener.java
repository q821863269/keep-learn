package cn.goduck.kl.design.pattern.behavior.observer.listeners;

import java.io.File;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 14:36
 */
public class LogOpenListener implements EventListener {

    private File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log " + log + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }

}
