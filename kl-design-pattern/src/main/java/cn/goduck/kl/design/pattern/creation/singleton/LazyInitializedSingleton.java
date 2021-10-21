package cn.goduck.kl.design.pattern.creation.singleton;

/**
 * Desc: 懒汉式单例
 * Author: Kon
 * Date: 2021/10/21 20:13
 */
public class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton() {
    }

    public static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }

}
