package cn.goduck.kl.design.pattern.behavior.state;

import cn.goduck.kl.design.pattern.behavior.state.ui.Player;
import cn.goduck.kl.design.pattern.behavior.state.ui.UI;

/**
 * Desc: 状态模式
 * Author: Kon
 * Date: 2021/11/7 11:27
 */
public class Demo {

    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }

}
