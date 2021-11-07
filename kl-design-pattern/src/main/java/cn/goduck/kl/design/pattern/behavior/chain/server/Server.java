package cn.goduck.kl.design.pattern.behavior.chain.server;

import cn.goduck.kl.design.pattern.behavior.chain.middleware.Middleware;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 11:56
 */
public class Server {

    private Map<String, String> userMap = new HashMap<>();
    private Middleware middleware;

    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    public boolean login(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("Authorization have been successful!");
            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        userMap.put(email, password);
    }

    public boolean hasEmail(String email) {
        return userMap.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return userMap.get(email).equals(password);
    }

}
