package cn.goduck.kl.design.pattern.behavior.observer.listeners;

import java.io.File;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 14:31
 */
public interface EventListener {

    void update(String eventType, File file);

}
