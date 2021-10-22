package cn.goduck.kl.design.pattern.structure.bridge.remote;

import cn.goduck.kl.design.pattern.structure.bridge.device.Device;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 14:53
 */
public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(Device device) {
        super.device = device;
    }

    /**
     * 静音
     */
    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }

}
