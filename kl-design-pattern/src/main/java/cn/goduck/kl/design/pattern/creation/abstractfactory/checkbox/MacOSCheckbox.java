package cn.goduck.kl.design.pattern.creation.abstractfactory.checkbox;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 16:57
 */
public class MacOSCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created MacOSCheckbox.");
    }

}
