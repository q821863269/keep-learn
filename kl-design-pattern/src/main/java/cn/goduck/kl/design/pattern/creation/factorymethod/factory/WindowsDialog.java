package cn.goduck.kl.design.pattern.creation.factorymethod.factory;

import cn.goduck.kl.design.pattern.creation.factorymethod.button.Button;
import cn.goduck.kl.design.pattern.creation.factorymethod.button.WindowsButton;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 16:16
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

}
