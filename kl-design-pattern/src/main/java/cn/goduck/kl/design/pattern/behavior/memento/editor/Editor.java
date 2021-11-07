package cn.goduck.kl.design.pattern.behavior.memento.editor;

import cn.goduck.kl.design.pattern.behavior.memento.command.Command;
import cn.goduck.kl.design.pattern.behavior.memento.history.History;
import cn.goduck.kl.design.pattern.behavior.memento.history.Memento;
import cn.goduck.kl.design.pattern.behavior.memento.shape.CompoundShape;
import cn.goduck.kl.design.pattern.behavior.memento.shape.Shape;

import javax.swing.*;
import java.io.*;
import java.util.Base64;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 19:12
 */
public class Editor extends JComponent {

    private Canvas canvas;
    private CompoundShape allShapes = new CompoundShape();
    private History history;

    public Editor() {
        canvas = new Canvas(this);
        history = new History();
    }

    public void loadShapes(Shape... shapes) {
        allShapes.clear();
        allShapes.add(shapes);
        canvas.refresh();
    }

    public CompoundShape getShapes() {
        return allShapes;
    }

    public void execute(Command c) {
        history.push(c, new Memento(this));
        c.execute();
    }

    public void undo() {
        if (history.undo())
            canvas.repaint();
    }

    public void redo() {
        if (history.redo())
            canvas.repaint();
    }

    public String backup() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.allShapes);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            return "";
        }
    }

    public void restore(String state) {
        try {
            byte[] data = Base64.getDecoder().decode(state);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            this.allShapes = (CompoundShape) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            System.out.print("ClassNotFoundException occurred.");
        } catch (IOException e) {
            System.out.print("IOException occurred.");
        }
    }

}
