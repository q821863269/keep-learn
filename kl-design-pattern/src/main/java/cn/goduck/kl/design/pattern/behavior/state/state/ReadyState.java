package cn.goduck.kl.design.pattern.behavior.state.state;

import cn.goduck.kl.design.pattern.behavior.state.ui.Player;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 15:48
 */
public class ReadyState extends State {

    public ReadyState(Player player) {
        super(player);
    }

    @Override
    public String onLock() {
        player.changeState(new LockedState(player));
        return "Locked...";
    }

    @Override
    public String onPlay() {
        String action = player.startPlayback();
        player.changeState(new PlayingState(player));
        return action;
    }

    @Override
    public String onNext() {
        return "Locked...";
    }

    @Override
    public String onPrevious() {
        return "Locked...";
    }

}
