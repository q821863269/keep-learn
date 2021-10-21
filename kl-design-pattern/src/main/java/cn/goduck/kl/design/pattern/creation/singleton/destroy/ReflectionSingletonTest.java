package cn.goduck.kl.design.pattern.creation.singleton.destroy;

import cn.goduck.kl.design.pattern.creation.singleton.EagerInitializedSingleton;

import java.lang.reflect.Constructor;

/**
 * Desc: 使用反射破坏单例模式
 * Author: Kon
 * Date: 2021/10/21 20:29
 */
public class ReflectionSingletonTest {

    public static void main(String[] args) {
        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;
        try {
            Constructor<?>[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                // Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(instanceOne.hashCode());
        if (instanceTwo != null) {
            System.out.println(instanceTwo.hashCode());
        }
    }

}
