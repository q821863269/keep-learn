package cn.goduck.kl.design.pattern.behavior.chain.middleware;

/**
 * Desc: 基础验证接口
 * Author: Kon
 * Date: 2021/11/7 11:50
 */
public abstract class Middleware {

    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }

}
