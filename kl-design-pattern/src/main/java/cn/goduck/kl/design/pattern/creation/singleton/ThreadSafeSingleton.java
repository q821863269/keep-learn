package cn.goduck.kl.design.pattern.creation.singleton;

/**
 * Desc: 线程安全单例
 * Author: Kon
 * Date: 2021/10/21 20:19
 */
public class ThreadSafeSingleton {

    private static volatile ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

}
