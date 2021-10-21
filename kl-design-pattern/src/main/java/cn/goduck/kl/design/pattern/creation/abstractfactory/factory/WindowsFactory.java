package cn.goduck.kl.design.pattern.creation.abstractfactory.factory;

import cn.goduck.kl.design.pattern.creation.abstractfactory.button.Button;
import cn.goduck.kl.design.pattern.creation.abstractfactory.button.WindowsButton;
import cn.goduck.kl.design.pattern.creation.abstractfactory.checkbox.Checkbox;
import cn.goduck.kl.design.pattern.creation.abstractfactory.checkbox.WindowsCheckbox;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 17:00
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

}
