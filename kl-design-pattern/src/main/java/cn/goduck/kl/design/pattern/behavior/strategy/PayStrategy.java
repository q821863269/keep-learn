package cn.goduck.kl.design.pattern.behavior.strategy;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 16:36
 */
public interface PayStrategy {

    boolean pay(int paymentAmount);
    void collectPaymentDetails();

}
