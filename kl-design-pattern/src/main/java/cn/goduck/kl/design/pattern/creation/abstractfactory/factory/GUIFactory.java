package cn.goduck.kl.design.pattern.creation.abstractfactory.factory;

import cn.goduck.kl.design.pattern.creation.abstractfactory.button.Button;
import cn.goduck.kl.design.pattern.creation.abstractfactory.checkbox.Checkbox;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 16:59
 */
public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

}
