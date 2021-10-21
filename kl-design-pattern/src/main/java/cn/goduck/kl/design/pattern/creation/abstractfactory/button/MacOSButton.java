package cn.goduck.kl.design.pattern.creation.abstractfactory.button;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 16:45
 */
public class MacOSButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created MacOSButton.");
    }

}
