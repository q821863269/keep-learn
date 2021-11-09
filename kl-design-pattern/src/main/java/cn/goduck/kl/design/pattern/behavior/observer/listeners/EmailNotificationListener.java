package cn.goduck.kl.design.pattern.behavior.observer.listeners;

import java.io.File;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 14:35
 */
public class EmailNotificationListener implements EventListener {

    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }

}
