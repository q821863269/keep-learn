package cn.goduck.kl.design.pattern.structure.adapter.square;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/22 14:27
 */
public class SquarePeg {

    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getSquare() {
        double result;
        result = Math.pow(this.width, 2);
        return result;
    }

}
