package cn.goduck.kl.design.pattern.behavior.chain.middleware;

import cn.goduck.kl.design.pattern.behavior.chain.server.Server;

/**
 * Desc: 检查用户登录信息
 * Author: Kon
 * Date: 2021/11/7 11:56
 */
public class UserExistsMiddleware extends Middleware {

    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(email, password);
    }

}
