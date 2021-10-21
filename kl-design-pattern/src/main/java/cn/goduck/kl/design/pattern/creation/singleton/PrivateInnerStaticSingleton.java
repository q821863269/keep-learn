package cn.goduck.kl.design.pattern.creation.singleton;

/**
 * Desc: 私有内部静态类单例
 * Author: Kon
 * Date: 2021/10/21 20:26
 */
public class PrivateInnerStaticSingleton {

    private PrivateInnerStaticSingleton() {
    }

    private static class SingletonHelper {
        private static final PrivateInnerStaticSingleton INSTANCE = new PrivateInnerStaticSingleton();
    }

    public static PrivateInnerStaticSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

}