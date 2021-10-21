package cn.goduck.kl.design.pattern.creation.abstractfactory.app;

import cn.goduck.kl.design.pattern.creation.abstractfactory.button.Button;
import cn.goduck.kl.design.pattern.creation.abstractfactory.checkbox.Checkbox;
import cn.goduck.kl.design.pattern.creation.abstractfactory.factory.GUIFactory;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 17:01
 */
public class Application {

    private Button button;

    private Checkbox checkbox;

    public Application(GUIFactory guiFactory) {
        this.button = guiFactory.createButton();
        this.checkbox = guiFactory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

}
