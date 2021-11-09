package cn.goduck.kl.design.pattern.behavior.state.state;

import cn.goduck.kl.design.pattern.behavior.state.ui.Player;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 15:48
 */
public class PlayingState extends State {

    PlayingState(Player player) {
        super(player);
    }

    @Override
    public String onLock() {
        player.changeState(new LockedState(player));
        player.setCurrentTrackAfterStop();
        return "Stop playing";
    }

    @Override
    public String onPlay() {
        player.changeState(new ReadyState(player));
        return "Paused...";
    }

    @Override
    public String onNext() {
        return player.nextTrack();
    }

    @Override
    public String onPrevious() {
        return player.previousTrack();
    }

}
