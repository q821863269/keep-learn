package cn.goduck.kl.design.pattern.behavior.memento.command;

import cn.goduck.kl.design.pattern.behavior.memento.editor.Editor;
import cn.goduck.kl.design.pattern.behavior.memento.shape.Shape;

import java.awt.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 19:11
 */
public class ColorCommand implements Command {

    private Editor editor;
    private Color color;

    public ColorCommand(Editor editor, Color color) {
        this.editor = editor;
        this.color = color;
    }

    @Override
    public String getName() {
        return "Colorize: " + color.toString();
    }

    @Override
    public void execute() {
        for (Shape child : editor.getShapes().getSelected()) {
            child.setColor(color);
        }
    }

}
