package cn.goduck.kl.design.pattern.creation.singleton;

/**
 * Desc: 静态块单例
 * Author: Kon
 * Date: 2021/10/21 19:43
 */
public class StaticBlockSingleton {

    private static StaticBlockSingleton instance;

    static {
        instance = new StaticBlockSingleton();
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }

}
