package cn.goduck.kl.design.pattern.creation.factorymethod.factory;

import cn.goduck.kl.design.pattern.creation.factorymethod.button.Button;

/**
 * Desc: 基础创建者（所以用抽象方法）
 * Author: Kon
 * Date: 2021/10/21 16:15
 */
public abstract class Dialog {

    public void renderWindow() {
        Button okButton = createButton();
        okButton.render();
    }

    public abstract Button createButton();

}
