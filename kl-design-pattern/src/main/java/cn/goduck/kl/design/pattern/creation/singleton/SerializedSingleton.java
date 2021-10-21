package cn.goduck.kl.design.pattern.creation.singleton;

import java.io.Serializable;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 20:43
 */
public class SerializedSingleton implements Serializable {

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton() {
    }

    private static class SingletonHelper {
        private static final SerializedSingleton instance = new SerializedSingleton();
    }

    public static SerializedSingleton getInstance() {
        return SingletonHelper.instance;
    }

    /**
     * 克服序列化破坏单例
     */
    protected Object readResolve() {
        return getInstance();
    }

}