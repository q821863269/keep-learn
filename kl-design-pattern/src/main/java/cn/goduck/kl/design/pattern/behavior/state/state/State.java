package cn.goduck.kl.design.pattern.behavior.state.state;

import cn.goduck.kl.design.pattern.behavior.state.ui.Player;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 15:35
 */
public abstract class State {

    Player player;

    State(Player player) {
        this.player = player;
    }

    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();

}
