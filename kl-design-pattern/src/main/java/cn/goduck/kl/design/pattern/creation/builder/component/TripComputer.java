package cn.goduck.kl.design.pattern.creation.builder.component;

import cn.goduck.kl.design.pattern.creation.builder.car.Car;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 17:29
 */
public class TripComputer {

    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public void showFuelLevel() {
        System.out.println("Fuel level: " + car.getFuel());
    }

    public void showStatus() {
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car is started");
        } else {
            System.out.println("Car isn't started");
        }
    }

}
