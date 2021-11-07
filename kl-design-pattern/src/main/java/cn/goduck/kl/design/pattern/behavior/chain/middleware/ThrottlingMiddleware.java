package cn.goduck.kl.design.pattern.behavior.chain.middleware;

/**
 * Desc: 检查请求数量限制
 * Author: Kon
 * Date: 2021/11/7 11:52
 */
public class ThrottlingMiddleware extends Middleware {

    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    @Override
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!");
            // 这里为了测试，使用了stop
            Thread.currentThread().stop();
        }

        return checkNext(email, password);
    }

}
