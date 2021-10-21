package cn.goduck.kl.design.pattern.creation.builder;

import cn.goduck.kl.design.pattern.creation.builder.builder.CarBuilder;
import cn.goduck.kl.design.pattern.creation.builder.builder.CarManualBuilder;
import cn.goduck.kl.design.pattern.creation.builder.car.Car;
import cn.goduck.kl.design.pattern.creation.builder.car.Manual;
import cn.goduck.kl.design.pattern.creation.builder.director.Director;

/**
 * Desc: 生成器模式
 *       生成器模式是一种创建型设计模式， 使你能够分步骤创建复杂对象。 该模式允许你使用相同的创建代码生成不同类型和形式的对象。
 * Author: Kon
 * Date: 2021/10/21 17:36
 */
public class Demo {

    public static void main(String[] args) {
        Director director = new Director();

        // Director gets the concrete builder object from the client
        // (application code). That's because application knows better which
        // builder to use to get a specific product.
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        // The final product is often retrieved from a builder object, since
        // Director is not aware and not dependent on concrete builders and
        // products.
        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.print());


        CarManualBuilder manualBuilder = new CarManualBuilder();
        // Director may know several building recipes.
        director.constructSportsCar(manualBuilder);

        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }

}
