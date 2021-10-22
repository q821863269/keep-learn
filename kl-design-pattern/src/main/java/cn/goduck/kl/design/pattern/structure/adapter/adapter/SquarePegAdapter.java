package cn.goduck.kl.design.pattern.structure.adapter.adapter;

import cn.goduck.kl.design.pattern.structure.adapter.round.RoundPeg;
import cn.goduck.kl.design.pattern.structure.adapter.square.SquarePeg;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 14:27
 */
public class SquarePegAdapter extends RoundPeg {

    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        double result;
        // 计算可以适合此钉的最小圆半径
        result = (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
        return result;
    }

}
