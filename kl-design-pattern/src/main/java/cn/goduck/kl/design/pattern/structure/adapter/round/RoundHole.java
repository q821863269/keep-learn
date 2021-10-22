package cn.goduck.kl.design.pattern.structure.adapter.round;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 13:56
 */
public class RoundHole {

    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg peg) {
        boolean result;
        result = (this.getRadius() >= peg.getRadius());
        return result;
    }

}
