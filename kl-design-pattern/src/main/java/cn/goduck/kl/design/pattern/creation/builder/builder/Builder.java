package cn.goduck.kl.design.pattern.creation.builder.builder;

import cn.goduck.kl.design.pattern.creation.builder.car.CarType;
import cn.goduck.kl.design.pattern.creation.builder.component.Engine;
import cn.goduck.kl.design.pattern.creation.builder.component.GPSNavigator;
import cn.goduck.kl.design.pattern.creation.builder.component.Transmission;
import cn.goduck.kl.design.pattern.creation.builder.component.TripComputer;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 17:27
 */
public interface Builder {

    void setCarType(CarType type);

    void setSeats(int seats);

    void setEngine(Engine engine);

    void setTransmission(Transmission transmission);

    void setTripComputer(TripComputer tripComputer);

    void setGPSNavigator(GPSNavigator gpsNavigator);

}
