package cn.goduck.kl.design.pattern.creation.builder.builder;

import cn.goduck.kl.design.pattern.creation.builder.car.Car;
import cn.goduck.kl.design.pattern.creation.builder.car.CarType;
import cn.goduck.kl.design.pattern.creation.builder.component.Engine;
import cn.goduck.kl.design.pattern.creation.builder.component.GPSNavigator;
import cn.goduck.kl.design.pattern.creation.builder.component.Transmission;
import cn.goduck.kl.design.pattern.creation.builder.component.TripComputer;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 17:32
 */
public class CarBuilder implements Builder {

    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }

}
