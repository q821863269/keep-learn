package cn.goduck.kl.design.pattern.creation.abstractfactory.factory;

import cn.goduck.kl.design.pattern.creation.abstractfactory.button.Button;
import cn.goduck.kl.design.pattern.creation.abstractfactory.button.MacOSButton;
import cn.goduck.kl.design.pattern.creation.abstractfactory.checkbox.Checkbox;
import cn.goduck.kl.design.pattern.creation.abstractfactory.checkbox.MacOSCheckbox;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 16:59
 */
public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }

}
