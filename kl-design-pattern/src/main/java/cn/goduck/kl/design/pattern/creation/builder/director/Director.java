package cn.goduck.kl.design.pattern.creation.builder.director;

import cn.goduck.kl.design.pattern.creation.builder.builder.Builder;
import cn.goduck.kl.design.pattern.creation.builder.car.CarType;
import cn.goduck.kl.design.pattern.creation.builder.component.Engine;
import cn.goduck.kl.design.pattern.creation.builder.component.GPSNavigator;
import cn.goduck.kl.design.pattern.creation.builder.component.Transmission;
import cn.goduck.kl.design.pattern.creation.builder.component.TripComputer;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 17:36
 */
public class Director {

    public void constructSportsCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(1.2, 0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.setEngine(new Engine(2.5, 0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
    }

}
