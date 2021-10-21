package cn.goduck.kl.design.pattern.creation.factorymethod.button;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 16:13
 */
public class HtmlButton implements Button {

    @Override
    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - 'Hello World!'");
    }

}
