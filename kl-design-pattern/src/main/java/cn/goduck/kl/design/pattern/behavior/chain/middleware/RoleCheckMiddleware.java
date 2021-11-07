package cn.goduck.kl.design.pattern.behavior.chain.middleware;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 12:01
 */
public class RoleCheckMiddleware extends Middleware {

    @Override
    public boolean check(String email, String password) {
        if ("admin@qq.com".equals(email)) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return checkNext(email, password);
    }

}
