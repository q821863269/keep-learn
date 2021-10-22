package cn.goduck.kl.design.pattern.structure.bridge.device;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 14:47
 */
public interface Device {

    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();

}
