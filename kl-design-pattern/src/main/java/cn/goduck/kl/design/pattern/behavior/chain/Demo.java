package cn.goduck.kl.design.pattern.behavior.chain;

import cn.goduck.kl.design.pattern.behavior.chain.middleware.Middleware;
import cn.goduck.kl.design.pattern.behavior.chain.middleware.RoleCheckMiddleware;
import cn.goduck.kl.design.pattern.behavior.chain.middleware.ThrottlingMiddleware;
import cn.goduck.kl.design.pattern.behavior.chain.middleware.UserExistsMiddleware;
import cn.goduck.kl.design.pattern.behavior.chain.server.Server;

import java.util.Scanner;

/**
 * Desc: 责任链模式
 *       允许你将请求沿着处理者链进行发送。 收到请求后， 每个处理者均可对请求进行处理， 或将其传递给链上的下个处理者。
 * Author: Kon
 * Date: 2021/11/7 11:27
 */
public class Demo {

    private static Scanner scanner = new Scanner(System.in);
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@qq.com", "123456");
        server.register("user@qq.com", "654321");

        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistsMiddleware(server))
                .linkWith(new RoleCheckMiddleware());

        server.setMiddleware(middleware);
    }

    public static void main(String[] args) {
        init();

        boolean success;
        do {
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Input password:");
            String password = scanner.nextLine();
            success = server.login(email, password);
        } while (!success);
    }

}
