package cn.goduck.kl.design.pattern.creation.singleton;

/**
 * Desc: 饿汉式单例
 * Author: Kon
 * Date: 2021/10/21 19:40
 */
public class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton() {
    }

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }

}
